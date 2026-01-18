package HRSD.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String loginUrl = "http://portal.individual.namaa.sumerge.com/login";

    private final String nationalIdHostId = "#national_id";
    private final String nationalIdTargetId = "#national_id_val";

    private final By loginButton = By.cssSelector(".ghost.large.width.center.hydrated");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLogin() {
        driver.get(loginUrl);
        waitForNetworkIdle(500, 10);
    }

    public void login(String idValue) throws InterruptedException {
        fillInputInShadow(nationalIdHostId, nationalIdTargetId, idValue);
        click(driver.findElement(loginButton));
    }
}