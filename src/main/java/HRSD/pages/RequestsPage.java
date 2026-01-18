package HRSD.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RequestsPage extends BasePage {

    private final String firstRequestNumberXpath = "(//*[@id='value_RequestsPage_RequestNumber'])[1]";

    public RequestsPage(WebDriver driver) {
        super(driver);
    }

    public String getRequestNumber() {
        try {

            WebElement element = waitForElementVisibleWithRetryXpath(firstRequestNumberXpath);

            String reqNum = element.getText();
            System.out.println("Extracted Request Number : " + reqNum);
            return reqNum;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for request number", e);
        } catch (Exception e) {
            throw new RuntimeException("Could not find Request Number element.", e);
        }
    }
}