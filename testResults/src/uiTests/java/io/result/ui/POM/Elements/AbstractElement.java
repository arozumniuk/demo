package io.result.ui.POM.Elements;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class for abstract element of the page.
 * Each wrapper for WebElement should extends this class.
 */

@AllArgsConstructor
public class AbstractElement {

    protected final int default_wait;
    protected final int default_implicite_wait;
    final By locator;
    final WebDriver driver;

    /**
     * This function performs searching for WebElement on the page by locator of this Element
     */

    public WebElement getWebElement() {
        try {
            //for element highlighting
            WebElement element = driver.findElement(locator);
            for (int i = 0; i < 2; i++) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].style.border='3px solid red'", element);
                js.executeScript("arguments[0].style.border=''", element);
            }
            //return element;
            //return driver.findElement(locator);
        } catch (Exception e) {

        }
        return driver.findElement(locator);
    }

    public List<WebElement> getWebElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * This function check immediately whether element is present on the page or not.
     * ImplicitlyWait has no impact on this function.
     */
    public boolean isPresent(By locator) {
        boolean isPresent = false;

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            isPresent = driver.findElements(locator).size() != 0;
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }

        return isPresent;
    }

    /**
     * This function check immediately whether element is present on the page and is visible.
     * ImplicitlyWait has no impact on this function.
     */
    public boolean isVisible(By locator) {
        boolean isDisplayed;

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            isDisplayed = getWebElement().isDisplayed();
        } catch (NullPointerException | StaleElementReferenceException e) {
            isDisplayed = getWebElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            restoreDefaultImplicitlyWait();
        }

        return isDisplayed;
    }

    /**
     * Waits until element appears on the page.
     *
     * @param seconds - int
     */
    public void waitForElementPresent(int seconds, By locator)
            throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, (seconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(driver, (seconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Wait until element appears on the page during default wait period
     *
     * @throws TimeoutException
     */
    public void waitForElementPresent(By locator)
            throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait

        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, (default_wait));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(driver, (default_wait));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes visible on the page.
     *
     * @param seconds how long to wait int seconds
     */
    public void waitForElementVisible(int seconds)
            throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes visible on the page during default wait period.
     */
    public AbstractElement waitForElementVisible()
            throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, default_wait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(driver, default_wait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
        return this;
    }

    /**
     * Waits until element becomes invisible on the page.
     *
     * @param seconds time in seconds
     */
    public void waitForElementIsNotVisible(int seconds, By locator)
            throws TimeoutException {

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            throw e;
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes invisible on the page during default wait period
     *
     * @throws TimeoutException
     */
    public void waitForElementIsNotVisible()
            throws TimeoutException {

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, default_wait);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            throw e;
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes clickable on the page.
     * NOTICE: works only with clickable elements.
     */
    public void waitForElementClickable()
            throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(driver, (default_wait));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, (default_wait));
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

    /**
     * This function designed to change default implicitlyWait of WebDriver.
     * It could be useful when realizing of custom flow is needed.
     * IMPORTANT: Do not forget to set default value using restoreDefaultImplicitlyWait function!!!
     *
     * @param sec time in seconds
     */
    protected void changeImplicitlyWait(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    /**
     * Restores default value of implicitlyWait for WebDriver using Config.
     */
    protected void restoreDefaultImplicitlyWait() {
        driver.manage().timeouts().implicitlyWait(default_implicite_wait, TimeUnit.SECONDS);
    }

}
