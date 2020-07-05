package io.result.ui.POM.Elements;

import io.result.ui.POM.Elements.AbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button extends AbstractElement {

    public Button(int default_wait, int default_implicite_wait, By locator, WebDriver driver) {
        super(default_wait, default_implicite_wait, locator, driver);
    }

    public void click() {
        getWebElement().click();
    }

    /**
     * Waits for element clickable, then clicks it.
     */
    public void waitForClickableAndClick(int seconds) {
        waitForElementClickable(seconds);
        click();
    }

    /**
     * Waits until element becomes clickable on the page.
     * NOTICE: works only with clickable elements.
     *
     * @param seconds - int
     */
    public void waitForElementClickable(int seconds)
            throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, (seconds));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, (seconds));
                wait.until(ExpectedConditions.elementToBeClickable(locator));
            } catch (WebDriverException e2) {
                throw e2;
            } finally {
                //restoring default value
                restoreDefaultImplicitlyWait();
            }
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    @Override
    public String toString() {
        return "Button{" +
                "locator=" + locator +
                '}';
    }
}

