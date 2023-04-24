package org.apidemo.pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    private String xPathHomeUIList = "//XCUIElementTypeStaticText";

    public void checkingNumberOfElements() {
        System.out.println("Inside Verify Sample Test");
        //driver.findElementByAccessibilityId("Activity Indicators").click();
        List<MobileElement> elements = driver.findElements(By.xpath(xPathHomeUIList));
        System.out.println(elements.size());

        for (MobileElement name : elements) {
            System.out.println(name.getAttribute("name"));
        }

    }


    public void clickOnHomeElements(String elementsName) {
        System.out.println("Inside clickOnHomeElements");
        //driver.findElementByAccessibilityId("Activity Indicators").click();
        List<MobileElement> elements = driver.findElements(By.xpath(xPathHomeUIList));

        for (MobileElement name : elements) {
            if (name.getAttribute("name").toString().contains(elementsName)) {
                clickElementsUsingName(elementsName);
                break;
            }

        }
    }

}


