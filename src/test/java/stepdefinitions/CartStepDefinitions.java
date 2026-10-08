package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductPage;
import utils.DriverFactory;

import java.util.List;

public class CartStepDefinitions {

    private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartPage;

    @When("the user adds the {string} to the cart")
    public void theUserAddsTheProductToTheCart(String productName) {

        driver = DriverFactory.getDriver();

        productPage = new ProductPage(driver);

        productPage.selectProduct(productName);
        productPage.addToCart();
    }

    @When("the user opens the shopping cart")
    public void theUserOpensTheShoppingCart() {

        productPage.clickShoppingCart();

        cartPage = new CartPage(driver);
    }

    @Then("the cart should display the product {string}")
    public void theCartShouldDisplayTheProduct(String expectedProduct) {

        Assert.assertEquals(
                cartPage.getCartProductName(),
                expectedProduct
        );
    }

    @Then("the cart should display the price {string}")
    public void theCartShouldDisplayThePrice(String expectedPrice) {

        Assert.assertEquals(
                cartPage.getCartProductPrice(),
                expectedPrice
        );
    }

    @Then("the cart should display the description {string}")
    public void theCartShouldDisplayTheDescription(
            String expectedDescription) {

        Assert.assertEquals(
                cartPage.getCartProductDescription(),
                expectedDescription
        );
    }

    @When("the user removes the product from the cart")
    public void theUserRemovesTheProductFromTheCart() {

        cartPage.removeProduct();
    }

    @Then("the product should not be displayed in the cart")
    public void theProductShouldNotBeDisplayedInTheCart() {

        Assert.assertFalse(
                cartPage.isProductDisplayed()
        );
    }

    @When("the user clicks the Checkout button")
    public void theUserClicksTheCheckoutButton() {

        cartPage.clickCheckout();
    }

    @Then("the Checkout page should be displayed")
    public void theCheckoutPageShouldBeDisplayed() {

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one")
        );
    }

    @When("the user clicks the Continue Shopping button")
    public void theUserClicksTheContinueShoppingButton() {

        cartPage.clickContinueShopping();
    }

    @When("the user adds the following products to the cart:")
    public void theUserAddsTheFollowingProductsToTheCart(
            DataTable dataTable) {

        driver = DriverFactory.getDriver();

        productPage = new ProductPage(driver);

        List<String> products =
                dataTable.asList();

        for (int i = 0; i < products.size(); i++) {

            productPage.selectProduct(products.get(i));
            productPage.addToCart();

            if (i < products.size() - 1) {
                productPage.clickBackToProducts();
            }
        }
    }

    @Then("the cart should contain the following products:")
    public void theCartShouldContainTheFollowingProducts(
            DataTable dataTable) {

        List<String> expectedProducts =
                dataTable.asList();

        List<String> actualProducts =
                cartPage.getCartProductNameTexts();

        Assert.assertEquals(
                actualProducts,
                expectedProducts
        );
    }
}