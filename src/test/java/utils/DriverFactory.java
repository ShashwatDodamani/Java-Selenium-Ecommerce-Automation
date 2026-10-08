package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createDriver() {

        ConfigReader configReader = new ConfigReader();

        String browser = System.getProperty(
                "browser",
                configReader.getProperty("browser")
        );

        String headless = System.getProperty(
                "headless",
                configReader.getProperty("headless")
        );

        if (browser == null || browser.isBlank()) {
            browser = "chrome";
        }

        if (headless == null || headless.isBlank()) {
            headless = "false";
        }

        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.setExperimentalOption(
                    "prefs",
                    Map.of(
                            "credentials_enable_service", false,
                            "profile.password_manager_leak_detection", false
                    )
            );

            if (Boolean.parseBoolean(headless)
                    || System.getenv("GITHUB_ACTIONS") != null) {

                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            webDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            webDriver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {

            webDriver = new EdgeDriver();

        } else {

            throw new RuntimeException(
                    "Unsupported browser: " + browser
            );
        }

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}