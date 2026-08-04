package org.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.phptravels.utils.WaitUtility;

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
//    @FindBy(xpath = "//span[@x-text = 'd.airportname || d.name']")
//    private List<WebElement> destinationSuggestions;
    private final By destinationSuggestions = By.xpath("//span[@x-text = 'd.airportname || d.name']");


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
    @FindBy(xpath = "//*[@id='st_guests_panel']//span[@x-text='room.adults']")
    private WebElement adultCount;
    @FindBy(xpath = "//span[@x-text='room.children']")
    private WebElement childrenCount;
    private final By adultIncreaseLocator =
            By.xpath("//div[normalize-space()='Adults']/ancestor::div[contains(@class,'justify-between')]//button[2]");

    private final By adultDecreaseLocator =
            By.xpath("//div[normalize-space()='Adults']/ancestor::div[contains(@class,'justify-between')]//button[1]");

    private final By childrenIncreaseLocator =
            By.xpath("//div[normalize-space()='Children']/ancestor::div[contains(@class,'justify-between')]//button[2]");

    private final By childrenDecreaseLocator =
            By.xpath("//div[normalize-space()='Children']/ancestor::div[contains(@class,'justify-between')]//button[1]");
    // TODO: Add after inspection

    // ======================================================
    // NATIONALITY
    // ======================================================
    @FindBy(id = "st_nat_trigger")
    private WebElement nationalityTrigger;
    @FindBy(id = "st_nat_q")
    private WebElement nationalitySearchBox;
    private final By nationalitySuggestions =
            By.xpath("//div[@id='st_nat_panel']//div[contains(@class,'cursor-pointer')]//span[contains(@class, 'text-sm')]");
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
        wait.waitForVisibleElement(destinationSuggestions);

        List<WebElement> suggestions = driver.findElements(destinationSuggestions);


        for(WebElement suggestion : suggestions){
            System.out.println("----------------");

            System.out.println("Displayed : " + suggestion.isDisplayed());

            System.out.println("Text : " + suggestion.getText());

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
//        WebElement nextButton = getVisibleNextButton();
//        wait.waitForVisibility(nextButton);
        getVisibleNextButton();
        navigateToMonth(date);
        clickTargetDay(date);

    }
    public void selectCheckOutDate(LocalDate date){
        navigateToMonth(date);
        clickTargetDay(date);
    }
    public void selectGuests(int targetAdultCount, int targetChildCount){
//        System.out.println("URL : " + driver.getCurrentUrl());
//        System.out.println("Contains Guests Trigger : " + driver.getPageSource().contains("st_guests_trigger"));
//        System.out.println("Displayed: " +guestsTrigger.isDisplayed());
//        System.out.println("enabled" + guestsTrigger.isEnabled() );
//        js.scrollIntoView(guestsTrigger);
        wait.waitForClickable(guestsTrigger);
//        System.out.println("Clicking Guests Trigger");
        guestsTrigger.click();
//        System.out.println("Guests Trigger Clicked");

        //Read Current adults
        int currentAdultCount = getCurrentAdultCount();
        while(currentAdultCount < targetAdultCount ){
            WebElement increaseAdultBtn = getVisibleElement(adultIncreaseLocator);
            wait.waitForClickable(increaseAdultBtn);
            increaseAdultBtn.click();
            wait.waitForTextToChange(adultCount, String.valueOf(currentAdultCount));
            currentAdultCount = getCurrentAdultCount();
        }
        while (currentAdultCount > targetAdultCount){
            WebElement decreaseAdultBtn = getVisibleElement(adultDecreaseLocator);
            wait.waitForClickable(decreaseAdultBtn);
            decreaseAdultBtn.click();
            wait.waitForTextToChange(adultCount, String.valueOf(currentAdultCount));
            currentAdultCount = getCurrentAdultCount();
        }

        //Read Current Children
        int currentChildCount = getCurrentChildCount();

        while(currentChildCount < targetChildCount ){
            WebElement increaseChildBtn = getVisibleElement(childrenIncreaseLocator);

            wait.waitForClickable(increaseChildBtn);
            increaseChildBtn.click();
            wait.waitForTextToChange(childrenCount, String.valueOf(currentChildCount));

            currentChildCount = getCurrentChildCount();
        }
        while (currentChildCount > targetChildCount){
            WebElement decreaseChildBtn = getVisibleElement(childrenDecreaseLocator);
            wait.waitForClickable(decreaseChildBtn);
            decreaseChildBtn.click();
            wait.waitForTextToChange(childrenCount, String.valueOf(currentChildCount));

            currentChildCount = getCurrentChildCount();
        }

    }
    public void selectNationality(String country){
        wait.waitForClickable(nationalityTrigger);
        nationalityTrigger.click();

        wait.waitForVisibility(nationalitySearchBox);
        nationalitySearchBox.sendKeys(country);

        wait.waitForVisibleElement(nationalitySuggestions);
        List<WebElement> suggestions = driver.findElements(nationalitySuggestions);
//        for(WebElement nation : suggestions){
//            if(nation.getText().trim().equalsIgnoreCase(country)){
//                System.out.println(nation.getText());
//                nation.click();
//                break;
//            }
//        }

        for (WebElement nation : suggestions) {

            System.out.println("--------------------");
            System.out.println("Displayed : " + nation.isDisplayed());
            System.out.println("Text      : '" + nation.getText() + "'");

            if (nation.getText().trim().equalsIgnoreCase(country)) {

                System.out.println("FOUND -> " + nation.getText());

                nation.click();

                break;
            }
        }
    }
    public HotelListingPage clickSearch(){
        wait.waitForClickable(clickSearchBtn);
        clickSearchBtn.click();

        return new HotelListingPage(driver);
    }




    ////Helpers !!!!
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
//        List<WebElement> nextButtons = driver.findElements(By.xpath("//th[contains(@class,'next')]"));
//        for(WebElement button : nextButtons){
////            System.out.println("--------------------");
////            System.out.println("Displayed : " + button.isDisplayed());
////            System.out.println("HTML : " + button.getAttribute("outerHTML"));
//            if(button.isDisplayed()){
//                return button;
//            }
//        }

        return wait.waitForVisibleElement(By.xpath("//th[contains(@class,'next')]"));
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

    private int getCurrentAdultCount(){
        System.out.println(adultCount.getAttribute("outerHTML"));
        System.out.println(adultCount.getText());
        return Integer.parseInt(adultCount.getText());
    }

    private int getCurrentChildCount(){
        return Integer.parseInt(childrenCount.getText());
    }

    private WebElement getVisibleElement(By locator){
        List <WebElement> elements = driver.findElements(locator);
        for(WebElement element : elements){
            if(element.isDisplayed()){
                return element;
            }
        }
        throw new RuntimeException("Visible Element not found.: " + locator);
    }
    private WebElement getVisibleAdultNextBtn(){
        List<WebElement> nextBtns = driver.findElements(By.xpath("//div[normalize-space()='Adults']/ancestor::div[contains(@class,'justify-between')]//button[2]"));
        for(WebElement nextBtn :nextBtns ){
            if(nextBtn.isDisplayed()){
                return nextBtn;
            }
        }
        throw new RuntimeException("Visible Adult Increase button not found.");


    }


}
