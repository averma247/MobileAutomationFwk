package org.apidemo;

import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import org.apidemo.pages.ios.AlertViewPage;
import org.apidemo.pages.ios.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

public class UIKitCatalogTest extends TestBase {

    HomePage homePage;
    AlertViewPage alertViewPage;

    @BeforeClass
    public void config() {
        System.out.println("Inside Before Class Config");
        homePage = new HomePage(getDriver());
        alertViewPage = new AlertViewPage(getDriver());
    }


    @Test(priority = 0)
    public void verifyHomePageElementTest() {

        test = extent.createTest("Verify Home Page").assignAuthor(prop.getProperty("AUTHOR")).assignCategory(prop.getProperty("TESTSUITE")).assignDevice(prop.getProperty("EXECUTIONPLATFORM"));
        test.log(Status.INFO, "Verify Home Page ");
        homePage.checkingNumberOfElements();


    }

    @Test(priority = 1)
    public void verifyAlertViews() {

        test = extent.createTest("Verify Alert Views Page").assignAuthor(prop.getProperty("AUTHOR")).assignCategory(prop.getProperty("TESTSUITE")).assignDevice(prop.getProperty("EXECUTIONPLATFORM"));
        test.log(Status.INFO, "Clicking on Home Element - Alert Views ");
        homePage.clickOnHomeElements("Alert Views");
        test.log(Status.INFO, "Handle Simple Alert");
        test.log(Status.INFO, "Handle Ok Cancel/Alert ");
        alertViewPage.handleSimpleAlert();
        alertViewPage.handleOkCancelAlert();

    }

    @Test(priority = 2)
    public void verifyActionSheets(){

        test = extent.createTest("Verify Action Sheet Alert").assignAuthor(prop.getProperty("AUTHOR")).assignCategory(prop.getProperty("TESTSUITE")).assignDevice(prop.getProperty("EXECUTIONPLATFORM"));
        test.log(Status.INFO, "Handle Action Sheet Alert.");
        Assert.assertEquals(true,alertViewPage.handleActionSheets());

    }




}
