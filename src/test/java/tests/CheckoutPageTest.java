
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;

public class CheckoutPageTest extends BaseTest {

    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    private static final String FIRST_NAME = "Shashwat";
    private static final String LAST_NAME = "Dodamani";
    private static final String POSTAL_CODE = "560001";

    private static final String CHECKOUT_INFORMATION_TITLE =
            "Checkout: Your Information";

    private static final String CHECKOUT_OVERVIEW_TITLE =
            "Checkout: Overview";

    private static final String FIRST_NAME_REQUIRED =
            "Error: First Name is required";

    private static final String LAST_NAME_REQUIRED =
            "Error: Last Name is required";

    private static final String POSTAL_CODE_REQUIRED =
            "Error: Postal Code is required";

    @Test(description = "Verify user can navigate to the Checkout: Your Information page")
    public void verifyCheckoutPageNavigation() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(BIKE_LIGHT);
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertEquals(
                checkoutPage.getCheckoutInformationTitle(),
                CHECKOUT_INFORMATION_TITLE
        );
    }

    @Test(description = "Verify user can enter valid checkout information and continue")
    public void verifyValidCheckoutInformationCanBeEntered() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(BIKE_LIGHT);
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(FIRST_NAME);
        checkoutPage.enterLastName(LAST_NAME);
        checkoutPage.enterPostalCode(POSTAL_CODE);
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getCheckoutOverviewTitle(),
                CHECKOUT_OVERVIEW_TITLE
        );
    }

    @Test(description = "Verify validation message is displayed when first name is empty")
    public void verifyCheckoutFailsWhenFirstNameIsEmpty() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(BIKE_LIGHT);
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterLastName(LAST_NAME);
        checkoutPage.enterPostalCode(POSTAL_CODE);
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                FIRST_NAME_REQUIRED
        );
    }

    @Test(description = "Verify validation message is displayed when last name is empty")
    public void verifyCheckoutFailsWhenLastNameIsEmpty() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(BIKE_LIGHT);
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(FIRST_NAME);
        checkoutPage.enterPostalCode(POSTAL_CODE);
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                LAST_NAME_REQUIRED
        );
    }

    @Test(description = "Verify validation message is displayed when postal code is empty")
    public void verifyCheckoutFailsWhenPostalCodeIsEmpty() {

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(BIKE_LIGHT);
        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(FIRST_NAME);
        checkoutPage.enterLastName(LAST_NAME);
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                POSTAL_CODE_REQUIRED
        );
    }
}
