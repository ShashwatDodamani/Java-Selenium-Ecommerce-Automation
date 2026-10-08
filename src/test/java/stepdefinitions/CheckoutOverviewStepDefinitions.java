package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CheckoutOverviewPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class CheckoutOverviewStepDefinitions {

    private WebDriver driver;

    private CheckoutOverviewPage overviewPage;

    private final ConfigReader configReader =
            new ConfigReader();

    @Then("the Checkout Overview should display the product {string}")
    public void theCheckoutOverviewShouldDisplayTheProduct(
            String expectedProduct) {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        Assert.assertEquals(
                overviewPage.getProductName(),
                expectedProduct
        );
    }

    @Then("the Checkout Overview should display the product name {string}")
    public void theCheckoutOverviewShouldDisplayTheProductName(
            String expectedProductName) {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        Assert.assertEquals(
                overviewPage.getProductName(),
                expectedProductName
        );
    }

    @Then("the Checkout Overview should display the product description {string}")
    public void theCheckoutOverviewShouldDisplayTheProductDescription(
            String expectedDescription) {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        Assert.assertEquals(
                overviewPage.getProductDescription(),
                expectedDescription
        );
    }

    @Then("the Checkout Overview should display the product price {string}")
    public void theCheckoutOverviewShouldDisplayTheProductPrice(
            String expectedPrice) {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        Assert.assertEquals(
                overviewPage.getProductPrice(),
                expectedPrice
        );
    }

    @Then("the payment information should be {string}")
    public void thePaymentInformationShouldBe(
            String expectedPaymentInformation) {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        Assert.assertEquals(
                overviewPage.getPaymentInformation(),
                expectedPaymentInformation
        );
    }

    @Then("the shipping information should be {string}")
    public void theShippingInformationShouldBe(
            String expectedShippingInformation) {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        Assert.assertEquals(
                overviewPage.getShippingInformation(),
                expectedShippingInformation
        );
    }

    @Then("the order total should equal the item total plus tax")
    public void theOrderTotalShouldEqualTheItemTotalPlusTax() {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        String itemTotalText =
                overviewPage.getItemTotal();

        String taxText =
                overviewPage.getTax();

        String totalText =
                overviewPage.getTotal();

        double itemTotal =
                Double.parseDouble(
                        itemTotalText.replace(
                                "Item total: $",
                                ""
                        )
                );

        double tax =
                Double.parseDouble(
                        taxText.replace(
                                "Tax: $",
                                ""
                        )
                );

        double actualTotal =
                Double.parseDouble(
                        totalText.replace(
                                "Total: $",
                                ""
                        )
                );

        double expectedTotal =
                itemTotal + tax;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01
        );
    }

    @When("the user clicks the Finish button")
    public void theUserClicksTheFinishButton() {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        overviewPage.clickFinish();
    }

    @Then("the order confirmation page should be displayed")
    public void theOrderConfirmationPageShouldBeDisplayed() {

        driver = DriverFactory.getDriver();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl")
                        + "checkout-complete.html"
        );
    }

    @When("the user clicks the Cancel button")
    public void theUserClicksTheCancelButton() {

        overviewPage =
                new CheckoutOverviewPage(
                        DriverFactory.getDriver()
                );

        overviewPage.clickCancel();
    }

    @Then("the Products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {

        driver = DriverFactory.getDriver();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl")
                        + "inventory.html"
        );
    }
}