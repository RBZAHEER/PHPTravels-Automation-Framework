package org.phptravels.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    public Boolean waitForInvisibility(WebElement element){
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
    /**
     * Wait until all elements in the list are visible.
     *
     * Useful for dynamic search results such as:
     * - Destination suggestions
     * - Destination suggestions
     * - Nationality suggestions
     * - Hotel search results
     *
     * @param elements List of WebElements
     * @return Visible list of WebElements
     */
    public List<WebElement> waitForVisibilityList(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement waitForVisibleElement(By locator) {

        return wait.until(driver -> {

            // Find all matching elements
            List<WebElement> elements = driver.findElements(locator);

            // Return the first visible one
            for (WebElement element : elements) {

                if (element.isDisplayed()) {
                    return element;
                }

            }

            // Returning null tells WebDriverWait:
            // "Condition not satisfied yet, keep polling."
            return null;
        });
    }
    public void waitForTextToChange(WebElement element, String oldValue) {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, oldValue)
        ));
    }

}