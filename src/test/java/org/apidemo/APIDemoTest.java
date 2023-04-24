package org.apidemo;

import org.testng.annotations.Test;

public class APIDemoTest extends TestBase{

    @Test
    public void sampleTest() {
        System.out.println("Inside Sample Test");
        driver.findElementById("android:id/button1").click();

    }
}
