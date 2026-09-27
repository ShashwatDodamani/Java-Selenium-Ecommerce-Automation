package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.databind.JsonNode;
import utils.ExtentTestListener;
import utils.JsonDataReader;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.DriverFactory;


@Listeners(ExtentTestListener.class)

public class BaseTest {

    public WebDriver driver;

    private final ConfigReader configReader = new ConfigReader();

    @BeforeSuite
    public void startReport() {
        ExtentReportManager.getReportInstance();
    }


    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.get(configReader.getProperty("baseUrl"));
    }

    protected void loginAsValidUser() {
        JsonDataReader reader = new JsonDataReader();
        JsonNode loginData = reader.readJson("validLoginCredentials.json");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                loginData.get("username").asText()
        );

        loginPage.enterPassword(
                loginData.get("password").asText()
        );

        loginPage.clickLogin();
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }


    @AfterSuite
    public void finishReport() {
        ExtentReportManager.getReportInstance().flush();
    }
}
