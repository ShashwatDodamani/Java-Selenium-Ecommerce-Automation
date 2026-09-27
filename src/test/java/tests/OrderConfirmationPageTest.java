package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.JsonDataReader;

public class OrderConfirmationPageTest extends BaseTest {

    private final ConfigReader configReader = new ConfigReader();

    @Test(description = "Verify the order confirmation page is displayed after completing checkout")
    public void verifyOrderConfirmationPage() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyOrderConfirmationPage.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName(testData.get("firstName").asText());
        checkoutPage.enterLastName(testData.get("lastName").asText());
        checkoutPage.enterPostalCode(testData.get("postalCode").asText());
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver);

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl") + "checkout-complete.html"
        );

        Assert.assertEquals(
                orderConfirmationPage.getAppLogo(),
                testData.get("appLogo").asText()
        );

        Assert.assertEquals(
                orderConfirmationPage.getPageTitle(),
                testData.get("orderConfirmationTitle").asText()
        );

        Assert.assertTrue(
                orderConfirmationPage.isPonyExpressDisplayed()
        );

        Assert.assertEquals(
                orderConfirmationPage.getCompleteHeader(),
                testData.get("orderConfirmationHeader").asText()
        );

        Assert.assertEquals(
                orderConfirmationPage.getCompleteText(),
                testData.get("orderDispatchMessage").asText()
        );

        Assert.assertEquals(
                orderConfirmationPage.getBackHomeButtonText(),
                testData.get("backHomeButton").asText()
        );

        Assert.assertEquals(
                orderConfirmationPage.getGeneratePdfButtonText(),
                testData.get("generatePdfOrderButton").asText()
        );
    }

    @Test(description = "Verify the Back Home button navigates to the Products page")
    public void verifyBackHomeButtonNavigation() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyBackHomeButtonNavigation.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName(testData.get("firstName").asText());
        checkoutPage.enterLastName(testData.get("lastName").asText());
        checkoutPage.enterPostalCode(testData.get("postalCode").asText());
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver);

        orderConfirmationPage.clickBackHome();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl") + "inventory.html"
        );
    }
    @Test(description = "Verify the Generate PDF Order button is displayed and can be clicked")
    public void verifyGeneratePdfOrderButton() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyGeneratePdfOrderButton.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName(testData.get("firstName").asText());
        checkoutPage.enterLastName(testData.get("lastName").asText());
        checkoutPage.enterPostalCode(testData.get("postalCode").asText());
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver);

        Assert.assertEquals(
                orderConfirmationPage.getGeneratePdfButtonText(),
                testData.get("generatePdfOrderButton").asText()
        );

        orderConfirmationPage.clickGeneratePdfOrder();
    }
}