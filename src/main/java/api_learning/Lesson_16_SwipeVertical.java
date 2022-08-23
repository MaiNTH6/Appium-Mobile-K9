package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import plaform.Platform;

import java.time.Duration;

public class Lesson_16_SwipeVertical {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Forms Screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginScreenBtn.click();

            // Wait until user is on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            // Get Mobile window Size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth /100;

            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight /100;

            // Convert coordinates --> Point Option
            PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint, yStartPoint);
            PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint, yEndPoint);

            //Using TouchAction to swipe up
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

//            //Swipe up from 90% --> 10%
//            Swipe.swipeVertically();
//
//            //Swipe up | step 10%, 5 times
//            Swipe.swipeVertically(10, 5);

            //Using TouchAction to swipe down | revert coodinates
            touchAction
                    .longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

            // Click the button
            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtnElem.click();


            //DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(appiumDriver != null) appiumDriver.quit();


    }

}
