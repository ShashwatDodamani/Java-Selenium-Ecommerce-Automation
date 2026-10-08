package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By locator) {
        wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }

    public void enterText(By locator, String text) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    public List<String> getTexts(By locator) {

        return wait.until(driver -> {

            List<WebElement> elements = driver.findElements(locator);

            if (elements.isEmpty()) {
                return null;
            }

            List<String> texts = new ArrayList<>();

            for (WebElement element : elements) {
                texts.add(element.getText());
            }

            return texts;
        });
    }

    public boolean isVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }
}