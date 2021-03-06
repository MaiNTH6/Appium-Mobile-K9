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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lesson_16_NarrowDownSearching {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

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

            int yStartPoint = 0;
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

            List<MobileElement> notificationElems = appiumDriver.findElements(MobileBy.id("android:id/notification_main_column"));
            Map<String, String> notificationContents = new HashMap<>();


            for (MobileElement notificationElem : notificationElems) {
//                MobileElement titleElem_ = notificationElem.findElements(MobileBy.id("android:id/text")).get(0);


                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/title"));
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/text"));

                notificationContents.put(titleElem.getText().trim(), contentElem.getText().trim());

                System.out.println("Title: " + titleElem.getText());
                System.out.println("Content: " + contentElem.getText());

            }

            //Vertification
            if(notificationContents.keySet().isEmpty())
                throw new RuntimeException("no notification");

            for (String title : notificationContents.keySet()) {
                System.out.println("Title: " + title);
                System.out.println(notificationContents.get(title));

            }

            //DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(appiumDriver != null) appiumDriver.quit();


    }

}
