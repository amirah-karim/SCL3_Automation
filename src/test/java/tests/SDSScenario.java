package tests;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class SDSScenario extends BaseTest_TETRA{

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

        //Input message
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/message_edit_text"),10).sendKeys("SDS Testing");

        //Tap NGCC contact
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/message_send_message_button"),20).click();
    }

    @Test (priority = 3)
    public void STMSendSDS(){
        //Tap Message Tab
        waitForClickable2(AppiumBy.accessibilityId("Message"),20).click();

        //Click new message
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/fabSendSds"),20).click();

        //Input ISSI Terminal B
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/acInd"),20).sendKeys("3204404");

        //Input SDS message
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/etData"),20).sendKeys("Test SDS from STM");

        //Tap send button
        waitForClickable2(AppiumBy.id("my.com.questrans.stm:id/ibSendMsg"),20).click();
    }

}
