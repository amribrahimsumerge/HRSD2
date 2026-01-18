package HRSD.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EntitiesRequestsPage extends BasePage {

    private final String uuidHostSelector = "dga-field#input_Uuid";

    private final String uuidTargetSelector = "input[placeholder='أدخل رقم الطلب']";

    private final By searchButton = By.id("button_RequestsPage_Search");

    private final String firstRequestNumberXpath = "(//*[@id='value_RequestsPage_RequestNumber'])[1]";

    public EntitiesRequestsPage(WebDriver driver) {
        super(driver);
    }

    public void searchForRequest(String requestNumber) throws InterruptedException {
        fillInputInShadow(uuidHostSelector, uuidTargetSelector, requestNumber);

        click(driver.findElement(searchButton));
        waitForNetworkIdle(500, 5);
    }

    public String getFirstRowRequestNumber() throws InterruptedException {
        WebElement element = waitForElementVisibleWithRetryXpath(firstRequestNumberXpath);

        String text = element.getText();
        System.out.println("Entities Portal - Extracted Request Number: " + text);
        return text;
    }
}