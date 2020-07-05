package io.result.ui.POM;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationPage extends AbstractPage {

    private static final By EMAIL_INPUT_LOCATOR = By.id("email");
    private static final By PASSWORD_INPUT_LOCATOR = By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");

    @Value("${url}")
    String url;

    @Override
    @Description("Open authentication page")
    public AuthenticationPage open() {
        super.open();
        driver.get(url + "?controller=my-account");
        return this;
    }

    @Description("Set login :: {login}")
    public AuthenticationPage setLogin(String login) {
        getInput(EMAIL_INPUT_LOCATOR).waitForElementVisible();
        getInput(EMAIL_INPUT_LOCATOR).sendKeys(login);
        return this;
    }

    @Description("Set password :: {password}")
    public AuthenticationPage setPassword(String password) {

        getInput(PASSWORD_INPUT_LOCATOR).waitForElementVisible();
        getInput(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        return this;
    }

    @Description("Click 'Sign in' button")
    public void clickSignIn() {
        getButton(SIGN_IN_BUTTON_LOCATOR).waitForClickableAndClick(1);
    }

    @Step("Sign in with email = {login} and password = {password}")
    public MyAccountPage signIn(String login, String password) {
        setLogin(login).setPassword(password).clickSignIn();
        checkPage(url + "?controller=my-account");
        return new MyAccountPage();
    }
}
