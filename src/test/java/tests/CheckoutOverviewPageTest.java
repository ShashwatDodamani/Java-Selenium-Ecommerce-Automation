package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.JsonDataReader;

public class CheckoutOverviewPageTest extends BaseTest {


    private final ConfigReader configReader = new ConfigReader();

    @Test(description = "Verify the selected product is displayed on the Checkout Overview page")
    public void verifySelectedProductIsDisplayedOnCheckoutOverview() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifySelectedProductIsDisplayedOnCheckoutOverview.json");

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

        Assert.assertEquals(
                overviewPage.getProductName(),
                testData.get("bikeLight").asText()
        );
    }


    @Test(description = "Verify product name, description, and price are displayed correctly on the Checkout Overview page")
    public void verifyProductDetailsOnCheckoutOverview() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyProductDetailsOnCheckoutOverview.json");

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

        Assert.assertEquals(
                overviewPage.getProductName(),
                testData.get("bikeLight").asText()
        );

        Assert.assertEquals(
                overviewPage.getProductDescription(),
                testData.get("bikeLightDescription").asText()
        );

        Assert.assertEquals(
                overviewPage.getProductPrice(),
                testData.get("bikeLightPrice").asText()
        );
    }

    @Test(description = "Verify payment and shipping information are displayed correctly")
    public void verifyPaymentAndShippingInformation() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyPaymentAndShippingInformation.json");

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

        Assert.assertEquals(
                overviewPage.getPaymentInformation(),
                testData.get("paymentInformation").asText()
        );

        Assert.assertEquals(
                overviewPage.getShippingInformation(),
                testData.get("shippingInformation").asText()
        );
    }

    @Test(description = "Verify order subtotal, tax, and total are calculated correctly")
    public void verifyOrderTotalCalculation() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyOrderTotalCalculation.json");

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

        String itemTotalText = overviewPage.getItemTotal();
        String taxText = overviewPage.getTax();
        String totalText = overviewPage.getTotal();

        double itemTotal = Double.parseDouble(
                itemTotalText.replace("Item total: $", "")
        );

        double tax = Double.parseDouble(
                taxText.replace("Tax: $", "")
        );

        double actualTotal = Double.parseDouble(
                totalText.replace("Total: $", "")
        );

        double expectedTotal = itemTotal + tax;

        Assert.assertEquals(actualTotal, expectedTotal, 0.01);
    }

    @Test(description = "Verify user can complete the order using the Finish button")
    public void verifyOrderCanBeCompletedUsingFinishButton() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyOrderCanBeCompletedUsingFinishButton.json");

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

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl") + "checkout-complete.html"
        );
    }
    @Test(description = "Verify the Cancel button returns the user to the Products page")
    public void verifyCancelButtonReturnsToCart() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyCancelButtonReturnsToCart.json");

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

        overviewPage.clickCancel();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl") + "inventory.html"
        );
    }
}
