package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import plaform.Platform;

import java.util.List;

public class Lesson_16_XpathLearning {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

    /* Làm sao để make sure là cái element không xuất hiện trên màn hình
    * Cách 1: dùng try catch
    * Nếu e == null: là tìm ra --> fail
    *   Exception e = null;
        try{
            MobileElement element = appiumDriver.findElement(MobileBy.AccessibilityId("taolao"))
        }catch (NoSuchElementException noSuchElementException){
            e =noSuchElementException;
        }
        if(e == null){      // e= null nghĩa là vẫn tìm ra --> show fail với lý do ...
            Assert.fail("reson ...")
        }

    *Cách 2: dùng findElements
    * Nếu empty: là không tìm ra --> ok
    * Nếu !emplty: tìm ra --> fail
    * List<MobileElement> elements = appiumDriver.findElements(MobileBy.AccessibilityId("taolao"));
    if(!elements.isEmpty())
        Assert.fail("reason .....");
    * */



        try {
            // Navigate to Login Screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            // Find all matching elements

            List<MobileElement> credFilesElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
            // Dùng index để truy cập từng phần tử bên trong với TH dùng xpath là chung(chung cho nhiều element)
            final int USERNAME_INDEX =0;
            final int PASSWORD_INDEX =1;
            credFilesElem.get(USERNAME_INDEX).sendKeys("teo@sth.com");
            credFilesElem.get(PASSWORD_INDEX).sendKeys("12345678");

            // Find login info text by UISelector - https://developer.android.com/reference/androidx/test/uiautomator/UiSelector
            /* Tìm theo Class.textContains......
             */
            MobileElement loginInstructionElem = appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionElem.getText());

            //DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(appiumDriver != null) appiumDriver.quit();


    }

}
