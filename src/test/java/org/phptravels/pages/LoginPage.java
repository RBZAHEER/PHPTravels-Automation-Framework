package org.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    //1. Username
    @FindBy(id = "email")
    private WebElement emailTextBox;
    //2. Password
    @FindBy(id = "password")
    private WebElement passwordTextBox;
    //loginbtn
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement loginBtn;

    //Actions
    public void enterEmail(String email){
        wait.waitForVisibility(emailTextBox);
        emailTextBox.sendKeys(email);
    }
    public void enterPassword(String password){
        wait.waitForVisibility(passwordTextBox);
        passwordTextBox.sendKeys(password);
    }
    public DashboardPage clickLoginBtn(){
        loginBtn.click();
        return new DashboardPage(driver);
    }

}
