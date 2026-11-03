package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;


public class BaseTest_TETRA {

    protected AndroidDriver driver, driver2;

    @BeforeTest
    public void setup() throws Exception {

        String appiumServerUrl = "http://127.0.0.1:4723";

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("SCL3")
                .setAutomationName("UiAutomator2")
                .setUdid("1PR002550GTM2S0")
                .setSystemPort(8201)
                .setNewCommandTimeout(Duration.ofSeconds(300));

        UiAutomator2Options options2 = new UiAutomator2Options()
                .setDeviceName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid("22815A0850")
                .setSystemPort(8202)
                .setNewCommandTimeout(Duration.ofSeconds(300));

        //device 1 driver
        driver = new AndroidDriver(new URL(appiumServerUrl), options);
        //device 1 driver settings
        driver.setSetting("enforceXPath1", true);

        Thread.sleep(5000);

        //device 2 driver
        driver2 = new AndroidDriver(new URL(appiumServerUrl), options2);
        //device 2 driver settings
        driver2.setSetting("enforceXPath1", true);
    }

    public WebElement waitForClickable(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForClickable2(By locator, int timeout) {
        return new WebDriverWait(driver2, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    @AfterTest
    public void tearDown() {

        //Get the apps package
        String SCL3APplication = driver.getCurrentPackage();

        //Close the current app open
        driver.terminateApp(SCL3APplication);

        //Get the apps package
        String STMApplication = driver2.getCurrentPackage();

        //CClose the current app open
        driver2.terminateApp(STMApplication);

        //terminate port 1
        if (driver != null) {
            driver.quit();
        }

        //terminate port 2
        if (driver2 != null) {
            driver2.quit();
        }
    }

    // sleep fallback (use only if needed)
    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

    public void holdElement(WebElement element, int seconds) {

        PointerInput finger =
                new PointerInput(PointerInput.Kind.TOUCH, "finger");

        int x = element.getLocation().getX()
                + element.getSize().getWidth() / 2;

        int y = element.getLocation().getY()
                + element.getSize().getHeight() / 2;

        Sequence hold = new Sequence(finger, 1);

        hold.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                x, y));

        hold.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()));

        hold.addAction(
                new Pause(finger,
                        Duration.ofSeconds(seconds)));

        hold.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()));

        driver2.perform(Collections.singletonList(hold));
    }

    public void pressAndHold(WebElement element, int holdSeconds) {

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        Sequence pressAndHold = new Sequence(finger, 1);

        // Move finger to PTT button
        pressAndHold.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.fromElement(element),
                        0,
                        0
                )
        );

        // Press down
        pressAndHold.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // Hold
        pressAndHold.addAction(
                new org.openqa.selenium.interactions.Pause(
                        finger,
                        Duration.ofSeconds(holdSeconds)
                )
        );

        // Release
        pressAndHold.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(Arrays.asList(pressAndHold));
    }

    public void openStrafon_STM(){

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), 420, 277));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver2.perform(Arrays.asList(tap));
    }

    public void openSepuraCC_SCL3(){

        //Open Sepura CC
        driver.findElement(AppiumBy.accessibilityId("Sepura CC")).click();
    }

    public void changeNetwork_SCL3(){

        sleep(1000);
        //Click Menu
        waitForClickable(AppiumBy.accessibilityId("Navigate up"),20).click();

        //Click Settings
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Settings\")")).click();

        sleep(1000);

        //Click Connectivity
        driver.findElement(AppiumBy.id("com.sepura.pmrui:id/settings_connectivity_option")).click();

        sleep(1000);

        //Select TMO
        waitForClickable(AppiumBy.id("com.sepura.pmrui:id/tmo_switch"),20).click();

    }

}
