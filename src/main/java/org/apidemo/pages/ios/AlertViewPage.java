package org.apidemo.pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AlertViewPage extends BasePage {
    public AlertViewPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void handleSimpleAlert(){

        clickElementsUsingName("Simple");
        clickElementsUsingName("OK");

    }
    public void handleOkCancelAlert(){
        clickElementsUsingName("Okay / Cancel");
        clickElementsUsingName("OK");
        clickElementsUsingName("Okay / Cancel");
        clickElementsUsingName("Cancel");

    }

    public boolean handleActionSheets(){
       if (verifyElementIsPresentUsingName("ACTION SHEET STYLE")){
           clickElementsUsingName("Confirm / Cancel");
           if(verifyElementIsPresentUsingName("Confirm")){
               clickElementsUsingName("Confirm");
               return true;
           }
           else
               return false;
       }
       else
           return false;

    }


}
