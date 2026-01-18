package HRSD.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EntitiesDashboardPage extends BasePage {

    private final String menuItemCss = "dga-navigation-group-item.default.light.hydrated";

    public EntitiesDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void openDisabilityAssessmentMenu() throws InterruptedException {
        waitForElementVisibleWithRetry(menuItemCss);

        List<WebElement> menuItems = driver.findElements(By.cssSelector(menuItemCss));

        if (menuItems.isEmpty()) {
            throw new RuntimeException("No menu items found with selector: " + menuItemCss);
        }

        if (menuItems.size() >= 2) {
            WebElement secondItem = menuItems.get(1);

//            scrollIntoView(secondItem);
            waitForVisibility(secondItem);
            click(secondItem);
        } else {
            System.out.println("Less than 2 items found (" + menuItems.size() + "). Clicking the 1st one.");
            click(menuItems.get(0));
        }
    }
}