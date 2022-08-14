package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;
import test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {

    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login(){
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();
        if(!username.isEmpty()) {
            loginFormComp.inputUsername(username);
        }

        if(!password.isEmpty()){
            loginFormComp.inputPassword(password);
        }

        loginFormComp.clickOnLoginBtn();

    }

    public void verifyLogin(){
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        boolean isEmailValid = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >=8;

        if(isEmailValid && isPasswordValid){
            verifyCorrectLoginCreds(loginFormComp);
        }

        if(!isEmailValid){
            verifyIncorrectEmail(loginFormComp);
        }

        if(!isPasswordValid){
            verifyIncorrectPassword(loginFormComp);
        }
    }

    // TODO: Homework
    @Step("Verify login with correct creds")
    private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {

        String actualValidEmailStr = loginFormComp.getValidEmailStr();
        String expectedValidEmailStr = "Success";
        Assert.assertEquals(actualValidEmailStr, expectedValidEmailStr, "Login success");

//        System.out.println("actualValidEmailStr: " + actualValidEmailStr);
//        System.out.println("expectedValidEmailStr: " + expectedValidEmailStr);
    }

    @Step("Verify login with Incorrect Email")
    private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
        String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
        String expectedInvalidEmailStr = "Please enter a valid email";
        Assert.assertEquals(actualInvalidEmailStr, expectedInvalidEmailStr, "[ERR] Invalid Email Str is not correct");

//        System.out.println("actualInvalidEmailStr: " + actualInvalidEmailStr);
//        System.out.println("expectedInvalidEmailStr: " + expectedInvalidEmailStr);
    }
    @Step("Verify login with Incorrect Password")
    private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
        String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
        String expectedInvalidPasswordStr = "Please enter at least 8 characters";
        Assert.assertEquals(actualInvalidPasswordStr, expectedInvalidPasswordStr, "[ERR] Invalid Password Str is not correct");

//        System.out.println("actualInvalidPasswordStr: " + actualInvalidPasswordStr);
//        System.out.println("expectedInvalidPasswordStr: " + expectedInvalidPasswordStr);
    }

}
