package test_automation;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookiesHelper {
    String ridValue;
    WebDriver driver;

    public CookiesHelper(WebDriver driver, @Value("${cookie.rid.value}") String ridValue) {
        this.ridValue = ridValue;
        this.driver = driver;
    }

    public void addCookieWithRid() {
        Cookie rid = new Cookie("rid", ridValue);
        driver.manage().addCookie(rid);
    }

    public void addCookieWithSigRid() {
        Cookie rid = new Cookie("rid", ridValue);
        driver.manage().addCookie(rid);
    }

    public void deleteRidCookie() {
        driver.manage().deleteCookieNamed("rid");
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
}
