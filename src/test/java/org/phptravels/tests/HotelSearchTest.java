package org.phptravels.tests;

import org.phptravels.base.BaseTest;
import org.phptravels.pages.DashboardPage;
import org.phptravels.pages.HomePage;
import org.phptravels.pages.HotelSearchPage;
import org.phptravels.pages.LoginPage;
import org.phptravels.utils.ConfigUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;

public class HotelSearchTest extends BaseTest {
    @Test
    public void verifyHotelSearch() throws IOException {
        //Delete Later once TestNG added
        HomePage homePage = new HomePage(driver);
        homePage.clickAck();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.enterEmail(ConfigUtility.getProperties("email"));
        loginPage.enterPassword(ConfigUtility.getProperties("password"));
        DashboardPage dashboardPage = loginPage.clickLoginBtn();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed());
        String username = dashboardPage.getUsername();
        Assert.assertEquals(username, ConfigUtility.getProperties("username"));

        //verify Hotel Search

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.clickStays();
        hotelSearchPage.selectDestination("Dubai");


        hotelSearchPage.selectCheckInDate(LocalDate.of(2026,10,20));
        hotelSearchPage.selectCheckOutDate(LocalDate.of(2026, 10, 25));

    }
}
