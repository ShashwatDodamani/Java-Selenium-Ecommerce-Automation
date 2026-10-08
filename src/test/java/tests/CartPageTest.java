
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;

import java.util.Arrays;
import java.util.List;

public class CartPageTest extends BaseTest {

    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";
    private static final String BIKE_LIGHT_PRICE = "$9.99";

    private static final String BIKE_LIGHT_DESCRIPTION =
        "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BOLT_T_SHIRT = "Sauce Labs Bolt T-Shirt";

    @Test(description = "Verify the selected product is displayed in the cart")
    public void verifyProductIsDisplayedInCart() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductName(),
                BIKE_LIGHT
        );
    }

    @Test(description = "Verify the product price is displayed in the cart")
    public void verifyProductPriceIsDisplayedInCart() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductPrice(),
                BIKE_LIGHT_PRICE
        );
    }

    @Test(description = "Verify the product description is displayed in the cart")
    public void verifyProductDescriptionIsDisplayedInCart() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductDescription(),
                BIKE_LIGHT_DESCRIPTION
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

        Assert.assertFalse(
                cartPage.isProductDisplayed()
        );
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

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectProduct(BIKE_LIGHT);
        productPage.addToCart();

        productPage.clickBackToProducts();

        productPage.selectProduct(BACKPACK);
        productPage.addToCart();

        productPage.clickBackToProducts();

        productPage.selectProduct(BOLT_T_SHIRT);
        productPage.addToCart();

        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);

        List<String> actualProductNames =
                cartPage.getCartProductNameTexts();

        Assert.assertEquals(
                actualProductNames.size(),
                3
        );

        List<String> expectedProductNames = Arrays.asList(
                BIKE_LIGHT,
                BACKPACK,
                BOLT_T_SHIRT
        );

        Assert.assertEquals(
                actualProductNames,
                expectedProductNames
        );
    }
}
