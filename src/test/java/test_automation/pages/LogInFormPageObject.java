package test_automation.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogInFormPageObject extends PageObject {
    By emailAddressInput = By.xpath("//form[contains(@data-ref, \"login_modal\")]//ry-input-d[contains(@name, \"email\")]//input[contains(@name, \"email\")]");
    By passwordInput = By.xpath("//input[contains(@name, \"password\")]");
    By logInBtn = By.xpath("//button[contains(@class, \"auth-submit__button\")]");

    public LogInFormPageObject(WebDriver driver,
                               SoftAssertions softAssertions,
                               @Value("${app.url}") String URL) {
        super(driver, softAssertions, URL);
    }

    public void loginWithCredentials(String userName, String password) {
        waitForObjectToBeLocated(emailAddressInput, 10);
        fillInFieldWithString(emailAddressInput, userName);
        fillInFieldWithString(passwordInput, password);
        waitForObjectToBeLocated(logInBtn, 5);
        driver.findElement(logInBtn).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
