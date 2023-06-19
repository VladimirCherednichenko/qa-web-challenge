package test_automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:Features/" },
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" },
        stepNotifications = true
)
@CucumberContextConfiguration
@SpringBootTest(classes =  AutomationApplication.class)
public class CucumberRunner {

}