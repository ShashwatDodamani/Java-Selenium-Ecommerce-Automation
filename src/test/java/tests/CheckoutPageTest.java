package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import utils.JsonDataReader;

public class CheckoutPageTest extends BaseTest {


    @Test(description = "Verify user can navigate to the Checkout: Your Information page")
    public void verifyCheckoutPageNavigation() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyCheckoutPageNavigation.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertEquals(
                checkoutPage.getCheckoutInformationTitle(),
                testData.get("checkoutInformationTitle").asText()
        );
    }

    @Test(description = "Verify user can enter valid checkout information and continue")
    public void verifyValidCheckoutInformationCanBeEntered() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyValidCheckoutInformationCanBeEntered.json");

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

        Assert.assertEquals(
                checkoutPage.getCheckoutOverviewTitle(),
                testData.get("checkoutOverviewTitle").asText()
        );
    }


    @Test(description = "Verify validation message is displayed when first name is empty")
    public void verifyCheckoutFailsWhenFirstNameIsEmpty() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyCheckoutFailsWhenFirstNameIsEmpty.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterLastName(testData.get("lastName").asText());
        checkoutPage.enterPostalCode(testData.get("postalCode").asText());
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                testData.get("firstNameRequired").asText()
        );
    }

    @Test(description = "Verify validation message is displayed when last name is empty")
    public void verifyCheckoutFailsWhenLastNameIsEmpty() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyCheckoutFailsWhenLastNameIsEmpty.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName(testData.get("firstName").asText());
        checkoutPage.enterPostalCode(testData.get("postalCode").asText());
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                testData.get("lastNameRequired").asText()
        );
    }
    @Test(description = "Verify validation message is displayed when postal code is empty")
    public void verifyCheckoutFailsWhenPostalCodeIsEmpty() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyCheckoutFailsWhenPostalCodeIsEmpty.json");

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
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                testData.get("postalCodeRequired").asText()
        );
    }
}
