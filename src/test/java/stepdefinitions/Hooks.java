package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;

public class Hooks {

    private final ConfigReader configReader = new ConfigReader();

    @Before
    public void setUp() {

        DriverFactory.createDriver();

        WebDriver driver = DriverFactory.getDriver();

        driver.manage().window().maximize();

        driver.get(
                configReader.getProperty("baseUrl")
        );
    }

    @After
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}