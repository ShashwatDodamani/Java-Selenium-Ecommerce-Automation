
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.ProductPage;
import utils.ConfigReader;

public class CheckoutOverviewPageTest extends BaseTest {

    private final ConfigReader configReader = new ConfigReader();

    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    private static final String BIKE_LIGHT_PRICE = "$9.99";

    private static final String BIKE_LIGHT_DESCRIPTION =
            "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";

    private static final String FIRST_NAME = "Shashwat";
    private static final String LAST_NAME = "Dodamani";
    private static final String POSTAL_CODE = "560001";

    private static final String PAYMENT_INFORMATION =
            "SauceCard #31337";

    private static final String SHIPPING_INFORMATION =
            "Free Pony Express Delivery!";

    @Test(description = "Verify the selected product is displayed on the Checkout Overview page")
    public void verifySelectedProductIsDisplayedOnCheckoutOverview() {

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

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getProductName(),
                BIKE_LIGHT
        );
    }

    @Test(description = "Verify product name, description, and price are displayed correctly on the Checkout Overview page")
    public void verifyProductDetailsOnCheckoutOverview() {

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

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getProductName(),
                BIKE_LIGHT
        );

        Assert.assertEquals(
                overviewPage.getProductDescription(),
                BIKE_LIGHT_DESCRIPTION
        );

        Assert.assertEquals(
                overviewPage.getProductPrice(),
                BIKE_LIGHT_PRICE
        );
    }

    @Test(description = "Verify payment and shipping information are displayed correctly")
    public void verifyPaymentAndShippingInformation() {

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

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getPaymentInformation(),
                PAYMENT_INFORMATION
        );

        Assert.assertEquals(
                overviewPage.getShippingInformation(),
                SHIPPING_INFORMATION
        );
    }

    @Test(description = "Verify order subtotal, tax, and total are calculated correctly")
    public void verifyOrderTotalCalculation() {

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

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01
        );
    }

    @Test(description = "Verify user can complete the order using the Finish button")
    public void verifyOrderCanBeCompletedUsingFinishButton() {

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

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        overviewPage.clickFinish();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl")
                        + "checkout-complete.html"
        );
    }

    @Test(description = "Verify the Cancel button returns the user to the Products page")
    public void verifyCancelButtonReturnsToCart() {

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

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        overviewPage.clickCancel();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl")
                        + "inventory.html"
        );
    }
}
