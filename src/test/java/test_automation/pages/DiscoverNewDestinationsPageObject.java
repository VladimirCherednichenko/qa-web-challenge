package test_automation.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.ArrayList;

@Component
public class DiscoverNewDestinationsPageObject extends PageObject {
    public DiscoverNewDestinationsPageObject(WebDriver driver, SoftAssertions softAssertions,@Value("${app.url}") String URL) {
        super(driver, softAssertions, URL);
    }
    By firstCountryExpandBtn = By.xpath("//ffr-countries-list/ffr-place-card[1]//span");
    By viewDestinationBtn = By.xpath("//ffr-countries-list/ffr-place-card[1]//ffr-cities-list[1]//button");
    By firstFlightOptionViewFlight = By.xpath("//ffr-flight-option[1]//button");

    public void expandFirstCountryInResults() {
        waitForObjectToBeLocated(firstCountryExpandBtn,10);
        driver.findElement(firstCountryExpandBtn).click();
        Logger.info("pressed first destination country");
    }

    public void pressViewDestination() {
        waitForObjectToBeLocated(viewDestinationBtn,5);
        driver.findElement(viewDestinationBtn).click();;
        Logger.info("pressed view destination");
    }

    public void pressFirstCityViewFlight() {
        driver.findElement(firstFlightOptionViewFlight).click();
        Logger.info("pressed view flight");
    }

    public void closeFirstTab()
    {
        waitForNumberOfWindowsToEqual(2);
        ArrayList<String> tabsArray = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabsArray.get(0));
        driver.close();
        driver.switchTo().window(tabsArray.get(1));
        waitForNumberOfWindowsToEqual(1);
    }
}
