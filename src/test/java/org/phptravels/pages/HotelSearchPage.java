package org.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

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
    @FindBy(xpath = "//th[@contains(@class, 'prev')]")
    private WebElement previousMonthButton;
    @FindBy(xpath = "//th[contains(@class,'next')]")
    private WebElement nextMonthButton;
    @FindBy(xpath = "//div[contains(@class,'datepicker-days')]//th[contains(@class,'switch')]")
    private List<WebElement> monthYearHeader;
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



    public void selectCheckInDate(LocalDate date) {
        wait.waitForClickable(checkInDate);
        checkInDate.click();
        WebElement nextButton = getVisibleNextButton();
        wait.waitForVisibility(nextButton);
        navigateToMonth(date);
        clickTargetDay(date);

    }
    public void selectCheckOutDate(LocalDate date){
        navigateToMonth(date);
        clickTargetDay(date);
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

    private String getDisplayedMonthYear() {

        List<WebElement> headers = driver.findElements(
                By.xpath("//div[contains(@class,'datepicker')]//th[contains(@class,'switch')]")
        );

        for (WebElement header : headers) {

            if (header.isDisplayed()) {
                return header.getText().trim();
            }

        }

        throw new RuntimeException("No visible calendar header found.");
    }

    private void navigateToMonth(LocalDate date) {

        String targetMonth =
                date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                        + " "
                        + date.getYear();

        String currentMonth = getDisplayedMonthYear();

        while (!currentMonth.equals(targetMonth)) {

            WebElement nextButton = getVisibleNextButton();
            nextButton.click();

            currentMonth = getDisplayedMonthYear();
        }
    }
    private WebElement getVisibleNextButton(){
        List<WebElement> nextButtons = driver.findElements(By.xpath("//th[contains(@class,'next')]"));
        for(WebElement button : nextButtons){
//            System.out.println("--------------------");
//            System.out.println("Displayed : " + button.isDisplayed());
//            System.out.println("HTML : " + button.getAttribute("outerHTML"));
            if(button.isDisplayed()){
                return button;
            }
        }
        throw new RuntimeException("Visible next button not found");
    }

    private void clickTargetDay(LocalDate date){
        int targetDay = date.getDayOfMonth();

        for(WebElement day : allDays) {
            if (!day.isDisplayed()) {
                continue;
            }

            String dayText = day.getText().trim();

            if (dayText.equals(String.valueOf(targetDay))) {
                wait.waitForClickable(day);
                day.click();
                return;
            }
        }
        throw new RuntimeException("Target Day " + targetDay + "not found");
    }
}
