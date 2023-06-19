package test_automation.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
@Component
public class MainPageObject extends PageObject{
    By destinationInput = By.id("input-button__destination");
    By departureInput = By.id("input-button__departure");
    By searchBtn = By.xpath("//button[@aria-label=\"Search\"]");
    By anyDestinationSpan = By.xpath("//span[@data-id=\"ANY\"]");
    By mondayFlexibleDatesBtn = By.xpath("//div[@class=\"flexible-dates__days\"]//fsw-element-item[contains(text(), Monday)]");
    By oneWayBtn = By.xpath("//label[contains(text(), \"One way\")]");
    By applyBtn = By.xpath("//button[contains(text(), \"Apply\")]");
    By yesIAgreeBtn = By.xpath("//button[contains(text(), \"Yes, I agree\")]");
    By agreedToTermsOfUseCheckbox = By.xpath("//ry-checkbox");
    By doneBtn = By.xpath("//button[@aria-label=\"Done\"]");
    By loginBtn = By.xpath("//button[@aria-label=\"Log In\"]");


    public MainPageObject(WebDriver driver, SoftAssertions softAssertions, @Value("${app.url}") String URL) {
        super(driver, softAssertions, URL);
    }

    public void pressLoginBtn() {
        driver.findElement(loginBtn).click();
    }
    public void navigateToMainPage() {
        driver.manage().window().maximize();
        driver.get(this.URL);
        Logger.info("navigate to main page");
    }

    public void agreeToCookies(){
        driver.findElement(yesIAgreeBtn).click();
        Logger.info("agreed to cookies");
    }

    public void selectAnyDestination() {
        driver.findElement(destinationInput).click();
        clickElementIfExists(anyDestinationSpan);
        Logger.info("selected any destination");
    }

    public void selectOneWayTrip() {
        waitForObjectToBeLocated(oneWayBtn, 5);
        driver.findElement(oneWayBtn).click();
        Logger.info("selected one way trip");
    }

    public void fillInDestination(String destinationName) {
        fillInFieldWithString(destinationInput, destinationName);
        Logger.info("filled in destination");
    }

    public void fillInDeparture(String departureName) {
        fillInFieldWithString(departureInput, departureName);
        Logger.info("filled in departure");
    }

    public void selectAirportWithName(String name) {
        By airportNameSpan = By.xpath("//span[@data-ref=\"airport-item__name\" and contains(text(),'"+ name +"')]");
        driver.findElement(airportNameSpan).click();
        Logger.info("selected airport");
    }

    public void pressSearchBtn() {
        driver.findElement(searchBtn).click();
        Logger.info("pressed search button");
    }

    public void selectNextMonthInFlexibleDates() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
        String currentMonthStr = simpleDateFormat.format(Calendar.getInstance().getTime());
        By currentMonthButton = By.xpath("//fsw-element-item[contains(text(), " + currentMonthStr + ")]");
        clickElementIfExists(currentMonthButton);
        Logger.info("chosen month");
    }

    public void selectMonthAndDayOfWeekInFlexibleDates(
            String month,
            String dayOfWeek)
    {
        By monthButton = By.xpath("//fsw-element-item[contains(text(), \"" + month + "\")]");
        clickElementIfExists(monthButton);
        Logger.info("chosen month");

        By dayOfWeekBtn = By.xpath("//div[@class=\"flexible-dates__days\"]//fsw-element-item[contains(text(), \"" + dayOfWeek + "\")]");

        driver.findElement(dayOfWeekBtn).click();
        Logger.info("chosen day of week");
    }

    public void selectMondayInFlexibleDates() {
        driver.findElement(mondayFlexibleDatesBtn).click();
        Logger.info("chosen monday");
    }

    public void clickApply() {
        driver.findElement(applyBtn).click();
        Logger.info("clicked apply");
    }

    public void agreeWithTermsOfUse(){
        clickElementIfExists(agreedToTermsOfUseCheckbox);
        Logger.info("agreed with terms of use");
    }

    public void clickDone(){
        driver.findElement(doneBtn).click();
        Logger.info("clicked done");
    }

}
