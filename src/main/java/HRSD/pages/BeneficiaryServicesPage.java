package HRSD.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BeneficiaryServicesPage extends BasePage {

    private final String servicesUrl = "http://portal.individual.namaa.sumerge.com/social-record/beneficiary-services";

    private final By serviceCard = By.cssSelector("app-landing-page-card[text='DISABILITY_ASSESSMENT_LANDING.SERVICE_CARD_TITLE']");

    private final By ctaButton = By.cssSelector("dga-button.svc-card__cta.primary.medium.isFullWidth.center.hydrated");

    private final String selectHostId = "dga-select#beneficiary";
    private final By selectHostLocator = By.id("beneficiary");

    private final By selectItem = By.cssSelector("dga-select-item[id='1000000008']");

    private final By checkButton = By.cssSelector("dga-button[id='check_btn']");

    private final By nextButton = By.cssSelector("dga-button.dark.small.width.center.hydrated");

    public BeneficiaryServicesPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToServices() {
        driver.get(servicesUrl);
        waitForNetworkIdle(500, 10);
    }

    public void selectDisabilityAssessmentService() {
        WebElement card = waitForVisibility(driver.findElement(serviceCard));
        scrollIntoView(card);
        click(card);
    }

    public void clickStartService() {
        WebElement btn = waitForVisibility(driver.findElement(ctaButton));
        click(btn);
    }

    public void fillBeneficiaryForm() throws InterruptedException {
        WebElement selectHost = waitForVisibility(driver.findElement(selectHostLocator));
        click(selectHost);

        WebElement item = waitForElementVisibleWithRetry("dga-select-item[id='1000000008']");
        click(item);
    }

    public void submitCheck() {
        WebElement btn = waitForVisibility(driver.findElement(checkButton));
        click(btn);
    }

    public void clickNextButton() {
        WebElement btn = waitForVisibility(driver.findElement(nextButton));
        click(btn);
    }
}