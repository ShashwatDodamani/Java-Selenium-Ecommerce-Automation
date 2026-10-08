
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.OrderConfirmationPage;
import pages.ProductPage;
import utils.ConfigReader;

public class OrderConfirmationPageTest extends BaseTest {

    private final ConfigReader configReader = new ConfigReader();

    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    private static final String FIRST_NAME = "Shashwat";
    private static final String LAST_NAME = "Dodamani";
    private static final String POSTAL_CODE = "560001";

    private static final String ORDER_CONFIRMATION_TITLE =
            "Checkout: Complete!";

    private static final String ORDER_CONFIRMATION_HEADER =
            "Thank you for your order!";

    private static final String BACK_HOME_BUTTON =
            "Back Home";

    private static final String GENERATE_PDF_ORDER_BUTTON =
            "Generate PDF order";

    @Test(description = "Verify the order confirmation page is displayed after completing checkout")
    public void verifyOrderConfirmationPage() {

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

        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver);

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl")
                        + "checkout-complete.html"
        );

        Assert.assertEquals(
                orderConfirmationPage.getPageTitle(),
                ORDER_CONFIRMATION_TITLE
        );

        Assert.assertTrue(
                orderConfirmationPage.isPonyExpressDisplayed()
        );

        Assert.assertEquals(
                orderConfirmationPage.getCompleteHeader(),
                ORDER_CONFIRMATION_HEADER
        );

        Assert.assertEquals(
                orderConfirmationPage.getBackHomeButtonText(),
                BACK_HOME_BUTTON
        );

        Assert.assertEquals(
                orderConfirmationPage.getGeneratePdfButtonText(),
                GENERATE_PDF_ORDER_BUTTON
        );
    }

    @Test(description = "Verify the Back Home button navigates to the Products page")
    public void verifyBackHomeButtonNavigation() {

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

        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver);

        orderConfirmationPage.clickBackHome();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                configReader.getProperty("baseUrl")
                        + "inventory.html"
        );
    }

    @Test(description = "Verify the Generate PDF Order button is displayed and can be clicked")
    public void verifyGeneratePdfOrderButton() {

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

        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver);

        Assert.assertEquals(
                orderConfirmationPage.getGeneratePdfButtonText(),
                GENERATE_PDF_ORDER_BUTTON
        );

        orderConfirmationPage.clickGeneratePdfOrder();
    }
}
