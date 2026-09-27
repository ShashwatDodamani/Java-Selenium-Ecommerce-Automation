package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By checkoutInformationTitle = By.cssSelector("[data-test='title']");
    private By firstNameField = By.cssSelector("[data-test='firstName']");
    private By lastNameField = By.cssSelector("[data-test='lastName']");
    private By postalCodeField = By.cssSelector("[data-test='postalCode']");
    private By continueButton = By.cssSelector("[data-test='continue']");
    private By checkoutOverviewTitle = By.cssSelector("[data-test='title']");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public String getCheckoutInformationTitle() {

        return getText(checkoutInformationTitle);
    }

    public void enterFirstName(String firstName) {

        enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {

        enterText(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {

        enterText(postalCodeField, postalCode);
    }

    public void clickContinue() {

        click(continueButton);
    }

    public String getCheckoutOverviewTitle() {

        return getText(checkoutOverviewTitle);
    }

    public String getErrorMessage() {

        return getText(errorMessage);
    }
}