package io.result.ui.POM;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.result.ui.POM.Elements.Button;
import io.result.ui.POM.Elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AbstractPage {
    @Value("${url}")
    String url;

    @Value("${default_wait}")
    int default_wait;

    @Value("${default_implicit_wait}")
    int default_implicit_wait;

    @Autowired
    public
    WebDriver driver;

    @Step("Open base page")
    public AbstractPage open() {
        driver.get(url);
        return this;
    }

    @Step("Navigate to logout page")
    public void logout() {
        driver.get(url + "?mylogout=");
        String currentUrl = getCurrentUrl();
        assertThat(currentUrl).isEqualTo(url);
    }


//GETTERS FOR WEB_ELEMENT WRAPPERS

    public Button getButton(By locator) {
        return new Button(default_wait, default_implicit_wait, locator, driver);
    }

    public Input getInput(By locator) {
        return new Input(default_wait, default_implicit_wait, locator, driver);
    }

    //COMMON FUNCTIONALITY

    /**
     * Returns current url of the page
     *
     * @return String - Current url
     */
    @Description("Get current url")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Try to avoid using this function!
     * Wrapper for Thread.sleep
     *
     * @param seconds - time in seconds
     */
    @Description("Sleeeeep")
    public static void sleep(int seconds) {

        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if current url is expected
     *
     * @param expectedUrl
     */
   // @Description("Check if url is {expected url}")
    public void checkPage(String expectedUrl) {
        if (!getCurrentUrl().equals(expectedUrl)) {
            throw new AssertionError("Current url is :: " + getCurrentUrl() + ". But expected :: " + expectedUrl);
        }
    }
}
