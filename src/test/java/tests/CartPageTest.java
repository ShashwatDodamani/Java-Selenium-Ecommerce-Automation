package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import utils.JsonDataReader;

import java.util.Arrays;
import java.util.List;

public class CartPageTest extends BaseTest {


    @Test(description = "Verify the selected product is displayed in the cart")
    public void verifyProductIsDisplayedInCart() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyProductIsDisplayedInCart.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductName(),
                testData.get("bikeLight").asText()
        );
    }


    @Test(description = "Verify the product price is displayed in the cart")
    public void verifyProductPriceIsDisplayedInCart() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyProductPriceIsDisplayedInCart.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductPrice(),
                testData.get("bikeLightPrice").asText()
        );
    }

    @Test(description = "Verify the product description is displayed in the cart")
    public void verifyProductDescriptionIsDisplayedInCart() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyProductDescriptionIsDisplayedInCart.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductDescription(),
                testData.get("bikeLightDescription").asText()
        );
    }

    @Test(description = "Verify user can remove a product from the cart")
    public void verifyProductIsRemovedFromCart() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct();

        Assert.assertFalse(cartPage.isProductDisplayed());

    }
    @Test(description = "Verify user can navigate to the Checkout page from the cart")
    public void verifyCheckoutNavigation() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one")
        );
    }


    @Test(description = "Verify user can continue shopping and return to the Products page")
    public void verifyContinueShoppingNavigatesToProducts() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory")
        );
    }


    @Test(description = "Verify multiple products are displayed in the cart")
    public void verifyMultipleProductsAreDisplayedInCart() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("verifyMultipleProductsAreDisplayedInCart.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectProduct(testData.get("bikeLight").asText());
        productPage.addToCart();

        productPage.clickBackToProducts();

        productPage.selectProduct(testData.get("backpack").asText());
        productPage.addToCart();

        productPage.clickBackToProducts();

        productPage.selectProduct(testData.get("boltTShirt").asText());
        productPage.addToCart();

        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        List<String> actualProductNames = cartPage.getCartProductNameTexts();

        Assert.assertEquals(
                actualProductNames.size(),
                testData.get("expectedCartProductCount").asInt()
        );

        List<String> expectedProductNames = Arrays.asList(
                testData.get("bikeLight").asText(),
                testData.get("backpack").asText(),
                testData.get("boltTShirt").asText()
        );

        Assert.assertEquals(actualProductNames, expectedProductNames);
    }
}
