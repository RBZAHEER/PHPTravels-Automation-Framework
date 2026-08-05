package org.phptravels.tests;

import org.phptravels.base.BaseTest;
import org.phptravels.pages.*;
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
//
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.clickStays();
        hotelSearchPage.selectDestination("Dubai");


        hotelSearchPage.selectCheckInDate(LocalDate.of(2026,10,20));
        hotelSearchPage.selectCheckOutDate(LocalDate.of(2026, 10, 25));

        //Click Guests
        hotelSearchPage.selectGuests(3,0);

        //Select nationality
        hotelSearchPage.selectNationality("united Arab Emirates");
        hotelSearchPage.clickSearch();
        //Hotel Listing Page
        HotelListingPage hotelListingPage = new HotelListingPage(driver);

//        Assert.assertTrue(hotelListingPage.isHotelListingDisplayed());

        Assert.assertTrue(hotelListingPage.isSearchResultDisplayed());

        Assert.assertTrue(hotelListingPage.areHotelCardsDisplayed());

        hotelListingPage.printHotelNames();

        hotelListingPage.clickFirstHotel();
        HotelDetailsPage hotelDetailsPage = new HotelDetailsPage(driver);

        hotelDetailsPage.selectFirstRoom();
        hotelDetailsPage.clickContinueBooking();

        //Booking Page

        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.acceptTermsAndConditions();
        bookingPage.clickConfirmBooking();
    }
}
