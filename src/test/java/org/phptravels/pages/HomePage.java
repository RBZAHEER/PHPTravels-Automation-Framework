package org.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    /**
     * Constructor
     * <p>
     * Every page object will call this constructor using:
     * <p>
     * super(driver);
     * <p>
     * Responsibilities:
     * <p>
     * 1. Store the WebDriver instance.
     * 2. Initialize all @FindBy elements using PageFactory.
     * 3. Initialize commonly used utilities.
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Locators
    //1. acknowledgeDemoWarning
    @FindBy(id = "acknowledgeDemoWarning")
    private WebElement ackBtn;
    //2. Login Btn
    @FindBy(xpath = "//a[contains(@href, 'login')]")
    private WebElement loginBtn;

    //Actions
    public void clickAck(){
        wait.waitForVisibility(ackBtn);

        if(ackBtn.isDisplayed()){
            ackBtn.click();

        }
        wait.waitForInvisibility(ackBtn);
    }
    public LoginPage clickLogin() {
        wait.waitForClickable(loginBtn);
        loginBtn.click();
        return new LoginPage(driver);
    }


}
