package test_automation;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(classes =  AutomationApplication.class)
public class CucumberHooks {
    String waitDuration;
    WebDriver driver;
    public CucumberHooks(WebDriver driver, @Value("${implicitWait.durationSeconds}") String waitDuration) {
        this.waitDuration = waitDuration;
        this.driver = driver;
    }


    @Before
    public void globalSetup() {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(waitDuration)));
    }
}
