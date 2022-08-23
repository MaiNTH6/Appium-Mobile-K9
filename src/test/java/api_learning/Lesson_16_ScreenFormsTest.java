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

public class Lesson_16_ScreenFormsTest {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Forms Screen
            MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtn.click();

            // Find Forms form elements
            MobileElement inputFieldElem = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            MobileElement switchElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            MobileElement dropDownElem = appiumDriver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.widget.EditText"));
            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"button-Active\"]/android.view.ViewGroup"));

            // Field Input field: input data
            inputFieldElem.sendKeys("data input");
            // Verification - Get text on screen forms
            MobileElement textResultField = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));
            System.out.println("Text inputed: " + textResultField.getText());
            // Field Switch: Click from OFF to ON
            switchElem.click();
            // Field Dropdown: Select option 1
            dropDownElem.click();

            MobileElement formsSelectOptionElem = appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"webdriver.io is awesome\")"));
            formsSelectOptionElem.click();

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

            //Using TouchAction to swipe down | revert coodinates
//            touchAction
//                    .longPress(endPoint)
//                    .moveTo(startPoint)
//                    .release()
//                    .perform();

//            Button: Click on the buton
            activeBtnElem.click();

            // Verification Button | Form dialog
            WebDriverWait waitForm= new WebDriverWait(appiumDriver, 10);
            waitForm.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));
            MobileElement formDialogTitleElem = appiumDriver.findElement(MobileBy.id("android:id/alertTitle"));
            MobileElement formDialodMessageElem = appiumDriver.findElement(MobileBy.id("android:id/message"));
            System.out.println("Title Dialog: " + formDialogTitleElem.getText());
            System.out.println("Content Dialog: " + formDialodMessageElem.getText());


            //DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(appiumDriver != null) appiumDriver.quit();


    }

}
