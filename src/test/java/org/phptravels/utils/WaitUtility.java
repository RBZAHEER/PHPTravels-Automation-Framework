package org.phptravels.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {
    private final WebDriverWait wait;
        //Initialize with default timeout durations
        public WaitUtility(WebDriver driver, int timeoutInSeconds){
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        }
        //Wait until element is present in DOM
        public WebElement waitForPresence(By locator){
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        //Wait for visibility
        public WebElement waitForVisibility(By locator){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }



        //Wait for Clickable
        public WebElement waitForClickable(WebElement element){
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }
}
