package tests;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class CallScenario extends BaseTest_TETRA{

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

    @Test (priority = 3)
    public void individualCall_SCL3(){
        //Go to Phonebook
        waitForClickable(AppiumBy.accessibilityId("Phonebook"),20).click();

        sleep(1000);

        //Tap Keypad
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/fab_open_dialler"),20).click();

        //Tap STM Number
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_three"),20).click();

        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_two"),20).click();

        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_zero"),20).click();

        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_four"),20).click();

        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_eight"),20).click();

        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_three"),20).click();

        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tv_dialler_zero"),20).click();

        sleep(1000);

        //Tap Call button
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/iv_dialler_call_button"),20).click();

        sleep(50000);
    }

    @Test (priority = 2)
    public void IndividualCall_STM(){

        //Tap Indv Tab
        waitForClickable2(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"Indv\")"
        ),20).click();

        //Key in SCL3 Number
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn3"),20).click();

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn2"),20).click();

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn0"),20).click();

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn4"),20).click();

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn4"),20).click();

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn0"),20).click();

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btn4"),20).click();

        sleep(1000);

        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/btnCall"),20).click();

        sleep(50000);
    }


}
