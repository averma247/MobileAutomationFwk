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


    @Test
    public void verifyHomePageTest() {

        test = extent.createTest("Verify Home Page");
        test.log(Status.INFO, "Verify Home Page ");
        homePage.checkingNumberOfElements();
        test.log(Status.INFO, "Clicking on Home Element - Alert Views ");
        homePage.clickOnHomeElements("Alert Views");
        test.log(Status.INFO, "Handle Simple Alert");
        test.log(Status.INFO, "Handle Ok Cancel/Alert ");
        alertViewPage.handleSimpleAlert();
        alertViewPage.handleOkCancelAlert();


    }




}
