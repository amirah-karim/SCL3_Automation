package tests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PTTCallScenario extends BaseTest_TETRA{

    @Test (priority = 1)
    public void openAppsBothDevice(){
        //open SCL3 apps
        //open Sepura CC
        openSepuraCC_SCL3();

        //change to TMO mode
        changeNetwork_SCL3();

        sleep(10000);

        //open STM apps
        //connect VPN
        openStrafon_STM();

        //InputISSI
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/txtId"),10).sendKeys("3204830");

        //Input password
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/txtPasswd"),10).sendKeys("123456");

        //Tap Indv Tab
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/mbLogin"),20).click();

        sleep(5000);
    }

    @Test (priority = 2)
    public void SCL3Ptt(){
        //Tap homescreen
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/nav_home"),20).click();

        sleep(1000);

        WebElement pttButton = driver.findElement(AppiumBy.accessibilityId("PTT"));

        pressAndHold(pttButton, 10);

        sleep(1000);

        //Tap hangup
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/home_hangup_button"),20).click();
    }


    @Test (priority = 3)
    public void STMPtt(){
        //Tapp PTT button
        holdElement(driver2.findElement(AppiumBy.id("my.com.questrans.stm:id/btnPtt")),15);

        sleep(1000);
    }
}
