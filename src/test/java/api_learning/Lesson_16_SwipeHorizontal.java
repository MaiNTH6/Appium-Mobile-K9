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

public class Lesson_16_SwipeHorizontal {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms Screen
            MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtn.click();

            // Wait until user is on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            // Get Mobile window Size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;

            int yStartPoint = 70 * screenHeight / 100;
            int yEndPoint = 70 * screenHeight /100;

            // Convert coordinates --> Point Option
            PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint, yStartPoint);
            PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint, yEndPoint);

            //Using TouchAction to swipe from Right to Left
            TouchAction touchAction = new TouchAction(appiumDriver);
            for (int time = 0; time < 5; time++) {
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
            }

            //Using TouchAction to swipe from Left to Right
            for (int time = 0; time < 5; time++) {
                touchAction
                        .press(endPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(startPoint)
                        .release()
                        .perform();
            }

            //DEBUG PURPOSE ONLY
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(appiumDriver != null) appiumDriver.quit();


    }

}
