package org.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingPage extends BasePage {

    public BookingPage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(xpath = "//input[@id='terms_accepted']/following-sibling::div")
    private WebElement termsCheck;

//    private final By termsCheck = By.xpath("//div[@class='checkbox-custom']");

    @FindBy(xpath = "//button[.//span[normalize-space() = 'Confirm Booking']]")
    private WebElement confirmBookingBtn;

    //Actions

    public void acceptTermsAndConditions(){
//        System.out.println("URL: " + driver.getCurrentUrl());
//        System.out.println("Title: " + driver.getTitle());
//
//        System.out.println("Terms Elements: " +
//                driver.findElements(By.id("terms_accepted")).size());
//
//        System.out.println("Frames: " +
//                driver.findElements(By.tagName("iframe")).size());

        System.out.println(driver.getPageSource().contains("terms_accepted"));
        System.out.println(driver.getPageSource().contains("Confirm Booking"));
        System.out.println(driver.getCurrentUrl());
//        js.scrollIntoView(termsCheck);

        wait.waitForClickable(termsCheck);
        js.clickElementByJS(termsCheck);
    }

    public PaymentPage clickConfirmBooking(){
        wait.waitForClickable(confirmBookingBtn);
        confirmBookingBtn.click();

        return new PaymentPage(driver);
    }
}