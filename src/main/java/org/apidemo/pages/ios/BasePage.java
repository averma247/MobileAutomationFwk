package org.apidemo.pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    protected AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver<MobileElement> driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
    }

    public void clickElementsUsingName(String idname){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByName(idname)));
        driver.findElementByName(idname).click();
    }

    public boolean verifyElementIsPresentUsingName(String idname){
        return  driver.findElementByName(idname).isDisplayed();
    }
}
