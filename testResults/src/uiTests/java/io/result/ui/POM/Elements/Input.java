package io.result.ui.POM.Elements;

import io.result.ui.POM.Elements.AbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Input extends AbstractElement {

    public Input(int default_wait, int default_implicite_wait, By locator, WebDriver driver) {
        super(default_wait, default_implicite_wait, locator, driver);
    }

    /**
     * Sends text to Input.
     *
     * @param text - String that has to be sent
     */
    public void sendKeys(String text) {
        if (text != null) {
            getWebElement().click();
            getWebElement().clear();
            getWebElement().sendKeys(text);
        }
    }

    public void sendKeys(Keys keys) {
        getWebElement().sendKeys(keys);
    }

    public void sendKeysWithoutClick(String text) {
        getWebElement().clear();
        getWebElement().sendKeys(text);
    }

    public void sendKeysWithoutClear(String text) {
        getWebElement().sendKeys(text);
    }

    /**
     * Waits until "value" parameter of Input contains specified text.
     */
    public void waitForTextIsPresentInElementValue(String text, int seconds) {
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
        } finally {
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Returns text that is inside of Input element
     */
    public String getText() {
        return getWebElement().getAttribute("value");
    }

}
