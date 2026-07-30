package org.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.List;

public class HotelSearchPage extends BasePage {
    public HotelSearchPage(WebDriver driver){
        super(driver);
    }

    //Locators
    //1. Click on Stays
    @FindBy(xpath = "//span[ normalize-space() = 'Stays']")
    private WebElement staysBtn;

    //2.Destination all locators
    @FindBy(id = "st_dest_trigger")
    private WebElement destinationTrigger;
    @FindBy(id = "st_dest_q")
    private WebElement destinationSearchBox;
    @FindBy(xpath = "//span[@x-text = 'd.airportname || d.name']")
    private List<WebElement> destinationSuggestions;


    //CheckIn and OUT
    @FindBy(name = "checkin_date")
    private WebElement checkInDate;
    @FindBy(xpath = "//th[@contains(@class, 'prev']")
    private WebElement previousMonthButton;
    @FindBy(xpath = "//th[contains(@class,'next')]")
    private WebElement nextMonthButton;
    @FindBy(xpath = "//th[contains(@class,'switch')]")
    private WebElement monthYearHeader;
    @FindBy(xpath = "//div[contains(@class,'day')]")
    private List<WebElement> allDays;

    //CheckOut
    @FindBy(name = "checkout_date")
    private WebElement checkoutDate;


    // ======================================================
    // GUESTS
    // ======================================================
    @FindBy(id = "st_guests_trigger")
    private WebElement guestsTrigger;
    @FindBy(xpath = "//div[normalize-space()='Adults']/ancestor::div[contains(@class,'justify-between')]//button[2]")
    private WebElement adultIncreaseBtn;
    @FindBy(xpath = "//div[normalize-space()='Adults']/ancestor::div[contains(@class,'justify-between')]//button[1]")
    private WebElement adultDecreaseBtn;
    @FindBy(xpath = "//span[@x-text='room.adults']")
    private WebElement adultCount;
    @FindBy(xpath = "//div[normalize-space()='Children']/ancestor::div[contains(@class,'justify-between')]//button[2]")
    private WebElement childrenIncreaseBtn;
    @FindBy(xpath = "//div[normalize-space()='Children']/ancestor::div[contains(@class,'justify-between')]//button[1]")
    private WebElement childrenDecreaseBtn;
    @FindBy(xpath = "//span[@x-text='room.children']")
    private WebElement childrenCount;
    // TODO: Add after inspection

    // ======================================================
    // NATIONALITY
    // ======================================================
    @FindBy(id = "st_nat_trigger")
    private WebElement nationalityTrigger;
    @FindBy(id = "st_nat_q")
    private WebElement nationalitySearchBox;
    @FindBy(xpath = "//div[@id = 'st_nat_panel']//div[contains(@class, 'cursor-pointer')]")
    private List<WebElement> nationalitySuggestions;
    // TODO: Add after inspection

    // ======================================================
    // SEARCH BUTTON
    // ======================================================
    @FindBy(xpath = "//button[@title= 'Search Hotels']")
    private WebElement clickSearchBtn;



    //Actions
    public void clickStays(){
        wait.waitForClickable(staysBtn);
        staysBtn.click();
    }
    public void selectDestination(String destination){
        wait.waitForClickable(destinationTrigger);
        destinationTrigger.click();

        wait.waitForClickable(destinationSearchBox);
        destinationSearchBox.sendKeys(destination);

        wait.waitForVisibilityList(destinationSuggestions);

        for(WebElement suggestion : destinationSuggestions){
            if(suggestion.getText().trim().equalsIgnoreCase(destination)){
                System.out.println(suggestion.getText());
                suggestion.click();
                break;
            }
        }
    }
    public void selectCheckInDate(LocalDate date){
        wait.waitForClickable(checkInDate);
        checkInDate.click();


    }
    public void selectCheckOutDate(LocalDate date){
        wait.waitForClickable(checkoutDate);
        checkoutDate.click();
    }
    public void selectGuests(int adults, int children){
        wait.waitForClickable(guestsTrigger);
        guestsTrigger.click();

    }
    public void selectNationality(String country){
        wait.waitForClickable(nationalityTrigger);
        nationalityTrigger.click();

        wait.waitForVisibility(nationalitySearchBox);
        nationalitySearchBox.sendKeys(country);

        wait.waitForVisibilityList(nationalitySuggestions);

    }
    public void clickSearch(){
        wait.waitForClickable(clickSearchBtn);
        clickSearchBtn.click();
    }


}
