package io.result.ui.POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class Banner extends AbstractPage {

    private static final By SIGN_OUT = By.className("logout");

    @Step("Click 'Sign out' button")
    public void clickSignOut() {
        getButton(SIGN_OUT).waitForClickableAndClick(1);
    }

}
