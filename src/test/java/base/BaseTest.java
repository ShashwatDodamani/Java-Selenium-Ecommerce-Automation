package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.ExtentTestListener;

@Listeners(ExtentTestListener.class)
public class BaseTest {

    protected WebDriver driver;

    private final ConfigReader configReader = new ConfigReader();

    private static final String VALID_USERNAME = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";

    @BeforeSuite
    public void startReport() {
        ExtentReportManager.getReportInstance();
    }

    @BeforeMethod
    public void setUp() {

        DriverFactory.createDriver();

        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();

        driver.get(configReader.getProperty("baseUrl"));
    }

    protected void loginAsValidUser() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void finishReport() {
        ExtentReportManager
                .getReportInstance()
                .flush();
    }
}