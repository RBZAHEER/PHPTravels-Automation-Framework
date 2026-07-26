package org.phptravels.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {

    // WebDriverWait instance used for all explicit waits
    private final WebDriverWait wait;

    /**
     * Constructor
     *
     * Initializes WebDriverWait with the timeout provided.
     *
     * @param driver Active WebDriver instance
     * @param timeoutInSeconds Explicit wait timeout
     */
    public WaitUtility(WebDriver driver, int timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Wait until the element is visible on the page.
     *
     * @param element WebElement to wait for
     * @return Visible WebElement
     */
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait until the element is clickable.
     *
     * @param element WebElement to wait for
     * @return Clickable WebElement
     */
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}