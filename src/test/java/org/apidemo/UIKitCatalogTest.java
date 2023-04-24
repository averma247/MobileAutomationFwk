package org.apidemo;

import io.appium.java_client.MobileElement;
import org.apidemo.pages.ios.AlertViewPage;
import org.apidemo.pages.ios.HomePage;
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


        homePage.checkingNumberOfElements();
        homePage.clickOnHomeElements("Alert Views");
        alertViewPage.handleSimpleAlert();
        alertViewPage.handleOkCancelAlert();
    }
}
