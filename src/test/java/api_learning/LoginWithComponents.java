package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import plaform.Platform;


public class LoginWithComponents {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Login screen
            LoginScreen loginScreen = new LoginScreen(appiumDriver);

            BottomNavComponent bottomNavComponent = loginScreen.bottomNavComp();
            LoginFormComponent loginFormComponent = loginScreen.loginFormComp();


            loginScreen.bottomNavComp().clickOnLoginIcon();
            loginScreen.loginFormComp().inputUsername("teo@sth.com");
            loginScreen.loginFormComp().inputPassword("12345678");
            loginScreen.loginFormComp().clickOnLoginBtn();


        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
