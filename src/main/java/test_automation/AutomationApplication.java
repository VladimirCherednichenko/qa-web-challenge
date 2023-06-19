package test_automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@SpringBootApplication
public class AutomationApplication {
    @Bean
    @Scope("singleton")
    public WebDriver webDriver() {
        return WebDriverManager.chromedriver().create();
    }
    @Bean
    public SoftAssertions softAssertions() {
        return new SoftAssertions();
    }
    public static void main(String[] args) {
        SpringApplication.run(AutomationApplication.class, args);
    }
}
