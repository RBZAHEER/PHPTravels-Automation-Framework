package org.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    //locators
    //1.Username on the page
    @FindBy(xpath = "//button//span[normalize-space()='Demo User']")
    private WebElement loggedInUser;

    //Actions
    public boolean isDashboardDisplayed() {
        wait.waitForVisibility(loggedInUser);
        return loggedInUser.isDisplayed();
    }
    public String getUsername() {
        wait.waitForVisibility(loggedInUser);
        return loggedInUser.getText();
    }

}
