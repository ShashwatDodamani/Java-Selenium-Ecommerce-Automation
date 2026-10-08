package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import utils.DriverFactory;

public class CheckoutStepDefinitions {

    private WebDriver driver;

    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @When("the user has {string} in the cart")
    public void theUserHasProductInTheCart(String productName) {

        driver = DriverFactory.getDriver();

        productPage = new ProductPage(driver);

        productPage.selectProduct(productName);
        productPage.addToCart();
    }

    @When("the user enters first name {string}, last name {string}, and postal code {string}")
    public void theUserEntersCheckoutInformation(
            String firstName,
            String lastName,
            String postalCode) {

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
    }

    @When("the user enters last name {string} and postal code {string}")
    public void theUserEntersLastNameAndPostalCode(
            String lastName,
            String postalCode) {

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
    }

    @When("the user enters first name {string} and postal code {string}")
    public void theUserEntersFirstNameAndPostalCode(
            String firstName,
            String postalCode) {

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterPostalCode(postalCode);
    }

    @When("the user enters first name {string} and last name {string}")
    public void theUserEntersFirstNameAndLastName(
            String firstName,
            String lastName) {

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
    }

    @When("the user clicks the Continue button")
    public void theUserClicksTheContinueButton() {

        checkoutPage.clickContinue();
    }

    @Then("the Checkout Information page should be displayed")
    public void theCheckoutInformationPageShouldBeDisplayed() {

        checkoutPage = new CheckoutPage(driver);

        Assert.assertEquals(
                checkoutPage.getCheckoutInformationTitle(),
                "Checkout: Your Information"
        );
    }

    @Then("the Checkout Overview page should be displayed")
    public void theCheckoutOverviewPageShouldBeDisplayed() {

        checkoutPage = new CheckoutPage(driver);

        Assert.assertEquals(
                checkoutPage.getCheckoutOverviewTitle(),
                "Checkout: Overview"
        );
    }

    @Then("the checkout error message {string} should be displayed")
    public void theCheckoutErrorMessageShouldBeDisplayed(
            String expectedMessage) {

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                expectedMessage
        );
    }
}