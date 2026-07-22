package org.phptravels.utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtility {
    private final JavascriptExecutor js;
    private final WebDriver driver;

    public JavaScriptUtility(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    //Click
    public void clickElementByJS(WebElement element){
        js.executeScript("arguments[0].click();",element);
    }

    //scrollIntoView
    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    // Scroll to the very bottom of the page
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
}
