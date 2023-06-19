package test_automation.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

@Component
public class CheckoutPageObject extends PageObject {
    By phoneNumberField = By.xpath("//div[contains(@class, \"contact-details\")]//input[contains(@name,\"phone-number\")]");
    By iDontWantToBeInsuredCheckbox = By.xpath("//ry-checkbox[@inputid=\"insurance-opt-out\"]");
    By cardNumberIFrame = By.xpath("//iframe[contains(@class,\"modal__credit-card-iframe\")]");
    By cardNumberField = By.xpath("//label[contains(text(),\"Card number\")]/..//input");
    By expiryDateField = By.xpath("//label[contains(text(),\"Expiry date\")]/..//input");
    By securityCodeField = By.xpath("//label[contains(text(),\"Security code\")]/..//input");
    By cardholderNameField = By.xpath("//label[contains(text(),\"Cardholder name\")]/..//input");
    By addressLine1Field = By.xpath("//label[contains(text(),\"Address line 1\")]/..//input");
    By addressLine2Field = By.xpath("//label[contains(text(),\"Address line 2\")]/..//input");
    By cityField = By.xpath("//label[contains(text(),\"City\")]/..//input");
    By countryField = By.xpath("//label[contains(text(),\"Country\")]/..//input");
    By postcodeField = By.xpath("//label[contains(text(),\"Postcode\")]/..//input");
    By currencyField = By.xpath("//span[contains(text(),\"Select a currency\")]");
    By currencyValue = By.xpath("//div[contains(@class,\"dropdown-item\")]//div[contains(text(),\"EUR\")]");
    By iHaveReadAndAcceptedCheckbox = By.xpath("//terms-and-conditions[contains(@class,\"terms-and-conditions\")]//ry-checkbox[contains(@class,\"terms-and-conditions\")]//input/following-sibling::div");
    By payNowBtn = By.xpath("//button[contains(text(),\"Pay now\")]");
    By paymentError = By.xpath("//div[contains(@class,\"payment-methods__error-message\")]//ry-alert[contains(@data-ref,\"payment-methods__error-message\")]");

    public CheckoutPageObject(WebDriver driver, SoftAssertions softAssertions, @Value("${app.url}") String URL) {
        super(driver, softAssertions, URL);
    }

    public void enterPhoneNumber(String phoneNumber)
    {
        waitForObjectToBeLocated(phoneNumberField, 10);
        fillInFieldWithString(phoneNumberField, phoneNumber);
        Logger.info("Entered phone number");
    }

    public void declineInsurance()
    {
        waitForObjectToBeLocated(iDontWantToBeInsuredCheckbox,10);
        driver.findElement(iDontWantToBeInsuredCheckbox).click();
        Logger.info("Clicked on I don't want to be insured checkbox");
    }

    public void enterCardDetails()
    {
        scrollUp();
        Logger.info("Scrolled to Payment methods");
        driver.switchTo().frame(driver.findElement(cardNumberIFrame));
        waitForObjectToBeLocated(cardNumberField, 10);
        fillInFieldWithString(cardNumberField, "5982761200000006");
        Logger.info("Switched to card number iFrame and entered card number");
        driver.switchTo().defaultContent();
        Logger.info("Switched back");
        fillInFieldWithString(expiryDateField, "0125");
        fillInFieldWithString(securityCodeField, "123");
        fillInFieldWithString(cardholderNameField, "John Doe");
        fillInFieldWithString(addressLine1Field, "Nathan Rd");
        fillInFieldWithString(addressLine2Field, "Kwun Chung");
        fillInFieldWithString(cityField, "Hong Kong");
        fillInFieldWithString(countryField, "China");
        WebElement countryFieldElement = driver.findElement(countryField);
        countryFieldElement.sendKeys(Keys.TAB);
        waitForObjectToBeLocated(postcodeField, 10);
        fillInFieldWithString(postcodeField, "853CWM");
        Logger.info("Entered all payment details");
    }

    public void enterCardDetails (String cardNumber,
                                 String expiryDate,
                                 String securityCode,
                                 String cardholderName,
                                 String firstAddress,
                                 String secondAddress,
                                 String city,
                                 String country,
                                 String postcode)
    {
        scrollUp();
        Logger.info("Scrolled to Payment methods");
        driver.switchTo().frame(driver.findElement(cardNumberIFrame));
        waitForObjectToBeLocated(cardNumberField, 10);
        fillInFieldWithString(cardNumberField, cardNumber);
        Logger.info("Switched to card number iFrame and entered card number");
        driver.switchTo().defaultContent();
        Logger.info("Switched back");
        fillInFieldWithString(expiryDateField, expiryDate);
        fillInFieldWithString(securityCodeField, securityCode);
        fillInFieldWithString(cardholderNameField, cardholderName);
        fillInFieldWithString(addressLine1Field, firstAddress);
        fillInFieldWithString(addressLine2Field, secondAddress);
        fillInFieldWithString(cityField, city);
        fillInFieldWithString(countryField, country);
        WebElement countryFieldElement = driver.findElement(countryField);
        countryFieldElement.sendKeys(Keys.TAB);
        waitForObjectToBeLocated(postcodeField, 10);
        fillInFieldWithString(postcodeField, postcode);
        Logger.info("Entered all payment details");
    }

    public void chooseCurrencyEuro()
    {
        waitForObjectToBeLocated(currencyField, 10);
        driver.findElement(currencyField).click();
        waitForObjectToBeLocated(currencyValue, 10);
        driver.findElement(currencyValue).click();
        Logger.info("Chosen euro as currency");
    }

    public void pressPay()
    {
        scrollUp();
        Logger.info("Scrolled to pay btn checkbox");
        waitForObjectToBeLocated(payNowBtn, 3);
        driver.findElement(payNowBtn).click();
        Logger.info("Pressed Pay now button");
    }

    public void acceptTermsOfUse()
    {
        scrollUp();
        Logger.info("Scrolled to Terms of Use checkbox");
        driver.findElement(iHaveReadAndAcceptedCheckbox).click();
        Logger.info("Pressed I accept term of use checkbox");
    }

    public void validatePaymentErrorMessageExistence()
    {
        waitForObjectToBeLocated(paymentError,10);
        Logger.info("payment error message exists");
    }

    public void validatePaymentErrorMsg(String msg)
    {
        By paymentErrorMsg = By.xpath("//div[contains(@class,\"payment-methods__error-message\")]//ry-alert[contains(@data-ref,\"payment-methods__error-message\")]/div[contains(text()," + msg + ")]");
        waitForObjectToBeLocated(paymentErrorMsg,3);
    }
}
