package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.OrderConfirmationPage;
import utils.DriverFactory;

public class OrderConfirmationStepDefinitions {

    private WebDriver driver;

    private OrderConfirmationPage orderConfirmationPage;

    private void initializePage() {

        driver = DriverFactory.getDriver();

        orderConfirmationPage =
                new OrderConfirmationPage(driver);
    }

    @Then("the order confirmation page title should be {string}")
    public void theOrderConfirmationPageTitleShouldBe(
            String expectedTitle) {

        initializePage();

        Assert.assertEquals(
                orderConfirmationPage.getPageTitle(),
                expectedTitle
        );
    }

    @Then("the order confirmation header should be {string}")
    public void theOrderConfirmationHeaderShouldBe(
            String expectedHeader) {

        initializePage();

        Assert.assertEquals(
                orderConfirmationPage.getCompleteHeader(),
                expectedHeader
        );
    }

    @Then("the Pony Express section should be displayed")
    public void thePonyExpressSectionShouldBeDisplayed() {

        initializePage();

        Assert.assertTrue(
                orderConfirmationPage.isPonyExpressDisplayed()
        );
    }

    @Then("the Back Home button should display {string}")
    public void theBackHomeButtonShouldDisplay(
            String expectedText) {

        initializePage();

        Assert.assertEquals(
                orderConfirmationPage.getBackHomeButtonText(),
                expectedText
        );
    }

    @Then("the Generate PDF button should display {string}")
    public void theGeneratePdfButtonShouldDisplay(
            String expectedText) {

        initializePage();

        Assert.assertEquals(
                orderConfirmationPage.getGeneratePdfButtonText(),
                expectedText
        );
    }

    @When("the user clicks the Back Home button")
    public void theUserClicksTheBackHomeButton() {

        initializePage();

        orderConfirmationPage.clickBackHome();
    }

    @When("the user clicks the Generate PDF Order button")
    public void theUserClicksTheGeneratePdfOrderButton() {

        initializePage();

        orderConfirmationPage.clickGeneratePdfOrder();
    }

    @Then("the Generate PDF Order button should remain displayed")
    public void theGeneratePdfOrderButtonShouldRemainDisplayed() {

        initializePage();

        Assert.assertEquals(
                orderConfirmationPage.getGeneratePdfButtonText(),
                "Generate PDF order"
        );
    }
}