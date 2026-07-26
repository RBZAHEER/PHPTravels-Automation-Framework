package org.phptravels.tests;

import org.openqa.selenium.support.FindBy;
import org.phptravels.base.BaseTest;
import org.phptravels.pages.DashboardPage;
import org.phptravels.pages.HomePage;
import org.phptravels.pages.LoginPage;
import org.phptravels.utils.ConfigUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test(invocationCount = 3)
    public void verifyCustomerCanLoginSuccessfully() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickAck();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.enterEmail(ConfigUtility.getProperties("email"));
        loginPage.enterPassword(ConfigUtility.getProperties("password"));
        DashboardPage dashboardPage = loginPage.clickLoginBtn();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed());
        String username = dashboardPage.getUsername();
        Assert.assertEquals(username, ConfigUtility.getProperties("username"));

    }


}
