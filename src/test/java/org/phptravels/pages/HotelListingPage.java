package org.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HotelListingPage extends BasePage{
    public HotelListingPage (WebDriver driver){
        super(driver);
    }

    //Locators
    //1. Is Listing Page is Displayed

    //Phase 2 – Verify search summary
    @FindBy(xpath = "//div[@class = 'container']")
    private WebElement searchResult;
    //Verify hotel cards
    @FindBy(xpath = "//div[@class = 'card overflow-hidden mb-3 p-0']")
    private List<WebElement> hotelCards;
    //Click on first card
    @FindBy(xpath = "//h3[@class = 'text-lg font-bold text-gray-900 dark:text-gray-100 mb-0.5 line-clamp-1']")
    private List<WebElement> hotelNames;
    // First More Details button
    @FindBy(xpath = "(//a[contains(@class,'btn')])[1]")
    private List<WebElement> moreDetailsButtons;



    //Actions
    //1. Is Listing Page is Displayed
    public boolean isHotelListingDisplayed(){
        System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl().contains("#stays");
    }

    //search result
    public boolean isSearchResultDisplayed(){
        wait.waitForVisibility(searchResult);
        return searchResult.isDisplayed();
    }

    //search result
    public boolean areHotelCardsDisplayed(){
        wait.waitForVisibilityList(hotelCards);
        return hotelCards.size() > 0;
        //Add logic code here
    }

    //Print Hotel Names
    public void printHotelNames(){
        wait.waitForVisibilityList(hotelNames);
        for(WebElement hotel : hotelNames){
            System.out.println(hotel.getText());
        }
    }

    //Click on moreDetails
    public HotelDetailsPage clickFirstHotel(){
        wait.waitForVisibilityList(moreDetailsButtons);
        if (moreDetailsButtons.isEmpty()) {
            throw new RuntimeException("No hotels found in listing.");
        }
        wait.waitForClickable(moreDetailsButtons.get(0));
        moreDetailsButtons.get(0).click();
        System.out.println("Clikdedddd");
        return new HotelDetailsPage(driver);
    }

}
