package org.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HotelDetailsPage extends BasePage{

    public HotelDetailsPage(WebDriver driver){
        super(driver);
    }
    //Locators
//    @FindBy(xpath = "//td[@class='px-4 py-3 text-center']//button")
//    private List<WebElement> selectBtns;

//

    private final By selectBtns =
        By.xpath("//td[contains(@class,'text-center')]//button");
    private final By continueBookingBtn =
            By.xpath("//button[contains(.,'Continue Booking')]");



    //Actions
    public void selectFirstRoom() {
        System.out.println(driver.getCurrentUrl());

        System.out.println(driver.getTitle());
        // Wait until at least one Select button is visible
        wait.waitForVisibleElement(selectBtns);

        List<WebElement> buttons = driver.findElements(selectBtns);

        if (buttons.isEmpty()) {
            throw new RuntimeException("No room available for booking.");
        }

        wait.waitForClickable(buttons.get(0));
        buttons.get(0).click();
    }


    public BookingPage clickContinueBooking() {

        WebElement button = wait.waitForVisibleElement(continueBookingBtn);
        wait.waitForClickable(button);
        button.click();

        System.out.println("Booking Page");
        return new BookingPage(driver);
    }

}
