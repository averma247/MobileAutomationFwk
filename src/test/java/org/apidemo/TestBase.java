package org.apidemo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class TestBase {

    public static AppiumDriver<MobileElement> driver;
    public static DesiredCapabilities caps;
    public static URL url;

    static Properties prop;


    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);
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
                    break;

                case "iOS":
                    initIOSDriver();
                    break;
                default:
                    System.out.println("Please provide correct platform like Android/iOS");
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
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,prop.getProperty("IOSPLATFORM_VERSION"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("IOSDEVICE_NAME"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("IOSAUTOMATION_NAME"));
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        caps.setCapability("fullReset", "false");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+prop.getProperty("IOSAPP_PATH"));


        url = new URL("http://127.0.0.1:4723");
        driver = new IOSDriver<MobileElement>(url, caps);


    }


    @AfterTest
    public void tearDown() {
        driver.quit();

    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

}
