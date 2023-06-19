package test_automation.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.ArrayList;

@Component
public class FlightDetailsPageObject extends PageObject {
    public FlightDetailsPageObject(WebDriver driver, SoftAssertions softAssertions,@Value("${app.url}") String URL) {
        super(driver, softAssertions, URL);
    }

    By signUpFormCloseBtn = By.xpath("//button[@class=\"close__button ng-star-inserted\"]");
    By continueForBtn = By.xpath("//div[contains(@class,\"fare-card-container\")]//button[contains(@class, \"fare-card__price\")]");
    By continueWithValueFareBtn = By.xpath("//button[contains(text(), \"Continue with Value fare\")]");
    By selectFlightBtn = By.xpath("//div[contains(@class,\"card-price\")]//button[contains(text(), \"Select\")]");
    By logInLaterBtn = By.xpath("//flights-passengers//span[contains(text(), \"Log in later\")]");
    By titleField = By.xpath("//pax-passenger-details-form-container//button[contains(@class, \"dropdown__toggle\")]");
    By firstNameField = By.id("form.passengers.ADT-0.name");
    By lastNameField = By.id("form.passengers.ADT-0.surname");
    By continueFlowBtn = By.xpath("//button[contains(@class, \"continue-flow__button\")]");
    By fastTrack = By.xpath("//p[contains(text(), \"Fast Track\")]");
    By busAndTrain = By.xpath("//p[contains(text(), \"BUS AND TRAIN\")]");
    By continueFlowFlightExtrasBtn = By.xpath("//button[contains(text(), \"Continue\")]");
    By continueFlowBagsBtn = By.xpath("//bags-continue-flow//button[contains(text(), \"Continue\")]");
    By selectSeatsLaterBtn = By.xpath("//h4[contains(text(), \"Option 2: Select seats later\")]");
    By continueWithoutASeatBtn = By.xpath("//reinforcement-message//button[contains(text(),\"Continue without a seat\")]");
    By oneSmallBagOnlyRadiobutton = By.xpath("//div[contains(@class, \"product-selector__control\")]/ry-radio-circle-button");
    By gotToCheckoutBtn = By.xpath("//button[contains(text(), \"Go to checkout\")]");
    By moreBagsModalNoThanks = By.xpath("//button[contains(text(), \"No, thanks\")]");

    public void chooseFirstFlightInTheList() {
        waitForObjectToBeLocated(selectFlightBtn, 5);
        driver.findElement(selectFlightBtn).click();
        Logger.info("pressed select first flight");
    }

    public void pressContinueForBasicPrice() {
        waitForObjectToBeLocated(continueForBtn, 5);
        driver.findElement(continueForBtn).click();
        Logger.info("pressed continue for basic price");
    }

    public void pressContinueWithValueFare() {
        waitForObjectToBeLocated(continueWithValueFareBtn, 5);
        driver.findElement(continueWithValueFareBtn).click();
        Logger.info("pressed continue with value fare");
    }

    public void pressLogInLater()
    {
        waitForObjectToBeLocated(logInLaterBtn, 20);
        driver.findElement(logInLaterBtn).click();
        Logger.info("pressed continue with value fare");
    }

    public void enterPassengerDetails(
            String firstName,
            String lastName,
            String title)
    {
        waitForObjectToBeLocated(firstNameField, 10);
        fillInFieldWithString(firstNameField, firstName);
        Logger.info("entered first name");
        fillInFieldWithString(lastNameField, lastName);
        Logger.info("entered last name");
        waitForObjectToBeLocated(titleField, 5);
        driver.findElement(titleField).click();
        Logger.info("opened title dropdown");

        By titleFieldValue = By.xpath("//pax-passenger-details-form-container//div[contains(@class, \"dropdown__menu\")]//div[contains(text(), \"" + title + "\")]");
        waitForObjectToBeLocated(titleFieldValue, 5);
        driver.findElement(titleFieldValue).click();
        Logger.info("chosen mr title");
    }

    public void clickContiniueFlowButton()
    {
        waitForObjectToBeLocated(continueFlowBtn, 5);
        driver.findElement(continueFlowBtn).click();
        Logger.info("pressed continue button");
    }

    public void selectSeatsLater()
    {
        waitForObjectToBeLocated(selectSeatsLaterBtn, 5);
        driver.findElement(selectSeatsLaterBtn).click();
        Logger.info("pressed select seats later");
        waitForObjectToBeLocated(continueWithoutASeatBtn, 5);
        driver.findElement(continueWithoutASeatBtn).click();
        Logger.info("pressed continue without a seat");
    }

    public void clickContinueInBagsSection()
    {
        waitForObjectToBeLocated(oneSmallBagOnlyRadiobutton, 10);
        driver.findElement(oneSmallBagOnlyRadiobutton).click();
        Logger.info("clicked on one small bag only");
        waitForObjectToBeLocated(continueFlowBagsBtn, 5);
        driver.findElement(continueFlowBagsBtn).click();
        Logger.info("pressed continue");
        waitForObjectToBeLocated(moreBagsModalNoThanks, 5);
        driver.findElement(moreBagsModalNoThanks).click();
        Logger.info("pressed no thanks on more bags pop up");
    }

    public void clickContinueInExtrasSectionAirportAndTrip()
    {
        waitForTabToBeLoaded();
        waitForObjectToBeLocated(fastTrack, 10);
        clickContinueInExtrasSection();
    }

    public void waitForTabToBeLoaded()
    {
        waitForNumberOfWindowsToEqual(1);
        ArrayList<String> tabsArray = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabsArray.get(0));
    }

    public void clickContinueInExtrasSection()
    {
        By continueFlowFlightExtrasBtn1 = By.xpath("//button[contains(text(), \"Continue\")]");
        driver.findElement(continueFlowFlightExtrasBtn1).click();
        Logger.info("pressed continue");
    }
    public void clickContinueInExtrasSectionTransport()
    {
        waitForTabToBeLoaded();
        //scrollUp();
        //waitForObjectToBeLocated(busAndTrain, 10);
        // not always bus/train option is available
        clickElementIfExists(busAndTrain);
        clickContinueInExtrasSection();
    }

    public void clickGoToCheckout()
    {
        waitForObjectToBeLocated(gotToCheckoutBtn, 5);
        driver.findElement(gotToCheckoutBtn).click();
        Logger.info("pressed go to checkout");
    }

    public void closeSignUpFormIfItExists() {
        clickElementIfExists(signUpFormCloseBtn);
    }

}
