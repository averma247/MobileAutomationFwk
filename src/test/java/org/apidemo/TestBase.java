package org.apidemo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class TestBase {

    public static AppiumDriver<MobileElement> driver;
    public static DesiredCapabilities caps;
    public static URL url;
    File extentReportFile;

    static ExtentReports extent;
    static ExtentSparkReporter spark;

    static ExtentTest test;

    static Properties prop;


    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);

            //------------------ Extent Report Settings -------------//
            extent = new ExtentReports();
            extentReportFile = new File(System.getProperty("user.dir") + "/Results/ExtentReport.html");
            spark = new ExtentSparkReporter(extentReportFile);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Mobile Automation Report Demo");
            spark.config().setReportName("Mobile Automation Demo");
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environment",prop.getProperty("ENVIRONMENT"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @BeforeTest
    public static void setUp() {
        try {
            caps = new DesiredCapabilities();
            String exePlatformName = prop.getProperty("EXECUTIONPLATFORM");

            switch (exePlatformName) {
                case "Android":
                    initAndroidDriver();
                    extent.setSystemInfo("Mobile OS", exePlatformName);
                    break;

                case "iOS":
                    initIOSDriver();
                    extent.setSystemInfo("Mobile OS", exePlatformName);
                    break;
                default:
                    System.out.println("Please provide correct platform like Android/iOS.");
            }

        } catch (Exception e) {
            System.out.println("Cause is: " + e.getCause());
            System.out.println("Message is: " + e.getMessage());
            e.printStackTrace();
        }


    }


    public static void initAndroidDriver() throws MalformedURLException {

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 6");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        caps.setCapability("fullReset", "true");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/src/test/resources/app/ApiDemos-debug.apk");


        url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver<MobileElement>(url, caps);


    }

    public static void initIOSDriver() throws MalformedURLException {
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("IOSPLATFORM_NAME"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("IOSPLATFORM_VERSION"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("IOSDEVICE_NAME"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("IOSAUTOMATION_NAME"));
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        caps.setCapability("fullReset", "false");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + prop.getProperty("IOSAPP_PATH"));


        url = new URL("http://127.0.0.1:4723");
        driver = new IOSDriver<MobileElement>(url, caps);


    }


    @AfterMethod
    public void after_Method(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getThrowable());
            test.log(Status.FAIL, "Test case is Failed.");
            //test.addScreenCaptureFromBase64String(getScreenShotFilePath());
            test.addScreenCaptureFromPath(getScreenShotFilePath());
        }

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Is Passed");

        }


    }

    @AfterTest
    public void tearDown() throws IOException {

        driver.quit();
        extent.flush();
        //Desktop.getDesktop().browse(extentReportFile.toURI());

    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    public String getScreenShotFilePath() throws IOException {

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());


        String base64ScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        String destinationScreenShotFilePath = System.getProperty("user.dir") + "/Screenshots/Screenshots_" + dateName + ".png";
        File destinationScreenShotFile = new File(destinationScreenShotFilePath);
        FileOutputStream fos = new FileOutputStream(destinationScreenShotFile);
        fos.write(Base64.decodeBase64(base64ScreenShot));

        return destinationScreenShotFilePath;
    }
}
