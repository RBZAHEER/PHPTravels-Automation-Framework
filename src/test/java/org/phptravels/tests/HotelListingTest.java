//package org.phptravels.tests;
//
//import org.phptravels.base.BaseTest;
//import org.phptravels.pages.HotelDetailsPage;
//import org.phptravels.pages.HotelListingPage;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class HotelListingTest extends BaseTest {
//    @Test
//    public void verifyHotelListing(){
//        HotelListingPage hotelListingPage = new HotelListingPage(driver);
//
//        Assert.assertTrue(hotelListingPage.isHotelListingDisplayed());
//
//        Assert.assertTrue(hotelListingPage.isSearchResultDisplayed());
//
//        Assert.assertTrue(hotelListingPage.areHotelCardsDisplayed());
//
//        hotelListingPage.printHotelNames();
//
//        HotelDetailsPage hotelDetailsPage =
//                hotelListingPage.clickFirstHotel();
//
//    }
//
//}
