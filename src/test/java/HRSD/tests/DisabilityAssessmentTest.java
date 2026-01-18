package HRSD.tests;

import HRSD.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DisabilityAssessmentTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private BeneficiaryServicesPage beneficiaryServicesPage;
    private RequestsPage requestsPage;

    private EntitiesLoginPage entitiesLoginPage;
    private EntitiesDashboardPage entitiesDashboardPage;
    private EntitiesRequestsPage entitiesRequestsPage;

    private String savedRequestNumber;

    @BeforeClass
    public void setUp() {
        String driverPath = "C:/Users/aragab/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        beneficiaryServicesPage = new BeneficiaryServicesPage(driver);
        requestsPage = new RequestsPage(driver);

        entitiesLoginPage = new EntitiesLoginPage(driver);
        entitiesDashboardPage = new EntitiesDashboardPage(driver);
        entitiesRequestsPage = new EntitiesRequestsPage(driver);
    }

    @Test(priority = 1)
    public void testLoginAndNavigation() throws InterruptedException {
        loginPage.navigateToLogin();
        loginPage.login("1000000008");
        Thread.sleep(10000);
        beneficiaryServicesPage.navigateToServices();
        Assert.assertTrue(driver.getCurrentUrl().contains("beneficiary-services"),
                "Failed to navigate to Beneficiary Services page");
    }

    @Test(priority = 2, dependsOnMethods = "testLoginAndNavigation")
    public void testServiceSubmission() throws InterruptedException {
        beneficiaryServicesPage.selectDisabilityAssessmentService();
        beneficiaryServicesPage.clickStartService();
        beneficiaryServicesPage.fillBeneficiaryForm();
        beneficiaryServicesPage.submitCheck();
        Thread.sleep(2000);
        beneficiaryServicesPage.clickNextButton();
    }

    @Test(priority = 3, dependsOnMethods = "testServiceSubmission")
    public void testExtractRequestNumber() throws InterruptedException {
        Thread.sleep(5000); // Stabilization wait
        savedRequestNumber = requestsPage.getRequestNumber();

        Assert.assertNotNull(savedRequestNumber, "Request Number should not be null");

    }

    @Test(priority = 4, dependsOnMethods = "testExtractRequestNumber")
    public void testEntitiesPortalVerification() throws InterruptedException {
        entitiesLoginPage.navigateToLogin();

        entitiesLoginPage.login("1999988879");
        Thread.sleep(9000);

        entitiesDashboardPage.openDisabilityAssessmentMenu();
        Thread.sleep(2000);

        entitiesRequestsPage.searchForRequest(savedRequestNumber);

        String entityTableValue = entitiesRequestsPage.getFirstRowRequestNumber();

        Assert.assertEquals(entityTableValue, savedRequestNumber,
                "Entities Portal request number does not match the one submitted!");

        System.out.println("Verification Successful: " + entityTableValue + " matches " + savedRequestNumber);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}