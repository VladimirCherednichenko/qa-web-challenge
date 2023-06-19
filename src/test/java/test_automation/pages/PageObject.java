package test_automation.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;


import static org.assertj.core.api.Assertions.assertThat;

public class PageObject {
    String URL;
    WebDriver driver;
    SoftAssertions softAssertions;

    public PageObject(WebDriver driver, SoftAssertions softAssertions, String URL) {
        this.driver = driver;
        this.softAssertions = softAssertions;
        this.URL = URL;
    }

    public void fillInFieldWithString(By elementLocator, String text) {
        WebElement field = driver.findElement(elementLocator);

        //field.clear();
        //String selectAll = Keys.chord(Keys.CONTROL, "a");
        //field.sendKeys(selectAll);
        //field.sendKeys(Keys.DELETE);

        while (!field.getAttribute("value").equals("")) {
            field.sendKeys(Keys.BACK_SPACE);
        }

        field.sendKeys(text);
    }
    public void checkErrorMsgUnderFld(By msgLocator, String expectedMsg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(msgLocator));
        String actualErrorMsg = driver.findElement(msgLocator).getText();
        assertThat(actualErrorMsg).isEqualTo(expectedMsg);
    }

    public void clickElementIfExists(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            driver.findElement(by).click();
        } catch (Exception e) {

        }
    }

    public void waitForObjectToBeLocated(By by, int seconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
        new WebDriverWait(driver, Duration.ofSeconds(3)) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }

    public void scrollUp(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)", "");
    }
}
