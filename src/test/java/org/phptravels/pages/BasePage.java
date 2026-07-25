package org.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.phptravels.utils.JavaScriptUtility;
import org.phptravels.utils.WaitUtility;

public class BasePage {
    /**
     * Step 1: What is a BasePage?
     *
     * Imagine you have these page classes:
     *
     * LoginPage
     * SearchPage
     * BookingPage
     * CheckoutPage
     *
     * What do they all have in common?
     *
     * • They all need WebDriver.
     * • They all need WaitUtility.
     * • They all need JavaScriptUtility.
     * • They all need PageFactory.initElements().
     *
     * Without a BasePage, every page would repeat the same code:
     *
     * this.driver = driver;
     * PageFactory.initElements(driver, this);
     * wait = new WaitUtility(driver, 20);
     * js = new JavaScriptUtility(driver);
     *
     * That's duplicate code.
     *
     * Instead, we write it once in BasePage.
     */
    protected WebDriver driver;
    protected WaitUtility wait;
    protected JavaScriptUtility js;
    /**
     * Constructor
     *
     * Every page object will call this constructor using:
     *
     * super(driver);
     *
     * Responsibilities:
     *
     * 1. Store the WebDriver instance.
     * 2. Initialize all @FindBy elements using PageFactory.
     * 3. Initialize commonly used utilities.
     */
    public BasePage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);

        wait = new WaitUtility(driver, 20);

        js = new JavaScriptUtility(driver);
    }








}
