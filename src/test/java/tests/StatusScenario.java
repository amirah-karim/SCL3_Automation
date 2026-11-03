package tests;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class StatusScenario extends BaseTest_TETRA{

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
    public void SCL3sendSDS(){
        //Tap Message Tab
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/nav_conversations"),20).click();

        //Tap +
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/fab_create_new_conversation"),20).click();

        //Tap NGCC contact
        waitForClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Ngcc\")"),20).click();

        //Tap Status icon
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/message_send_status_button"),20).click();

        //Select Status
        waitForClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Stand Down\")"),20).click();

        //Tap select
        waitForClickable(AppiumBy.id("android:id/button1"),20).click();

    }

    @Test (priority = 2)
    public void STMSendSDS(){
        //Tap Message Tab
        waitForClickable2(AppiumBy.accessibilityId("Message"),20).click();

        //Tap New Message
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/fabSendSds"),20).click();

        //Input ISSI Terminal B
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/acInd"),20).sendKeys("3204404");

        //Click status icon
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/ibStsMsgList"),20).click();

        //Select status
        waitForClickable2(AppiumBy.androidUIAutomator("new UiSelector().text(\"Stand Down\")"),20).click();

        //Click send
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/ibSendMsg"),20).click();
    }

}
