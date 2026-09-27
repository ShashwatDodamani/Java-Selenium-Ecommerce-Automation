package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import utils.JsonDataReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ProductPageTest extends BaseTest {


    @Test(description = "Verify all products are displayed on the Products page")
    public void verifyProductsAreDisplayed(){

        loginAsValidUser();
        ProductPage productPage = new ProductPage(driver);
        List<String> products = productPage.getProductNameTexts();
        Assert.assertTrue(products.size() > 0);
    }

    @Test(description = "Verify all products have a product title")
    public void verifyAllProductsHaveTitle(){
        loginAsValidUser();
        ProductPage productPage = new ProductPage(driver);
        // Get all product title elements
        List<String> names = productPage.getProductNameTexts();

        for (String name : names){
            Assert.assertFalse(name.isEmpty());
        }
    }

    @Test(description = "Verify all products have a product description")
    public void verifyAllProductsHaveDescription(){
        loginAsValidUser();
        ProductPage productPage = new ProductPage(driver);

        List<String> descriptions = productPage.getProductDescriptionTexts();

        for (String description : descriptions){
            Assert.assertFalse(description.isEmpty());
        }
    }

    @Test(description = "Verify all products have a valid price")
    public void verifyAllProductsHavePrice(){
        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);

        List<String> priceValues = productPage.getProductPriceTexts();

        for (String price : priceValues){
            Assert.assertFalse(price.isEmpty());
        }
    }

    @Test(description = "Verify user can select the Sauce Labs Bike Light product")
    public void selectBikeLightTest() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("selectBikeLightTest.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();

        Assert.assertEquals(
                productPage.productDetailsTitle(),
                testData.get("bikeLight").asText()
        );

        productPage.addToCart();
        productPage.clickShoppingCart();

        CartPage cartpage = new CartPage(driver);

        Assert.assertEquals(
                cartpage.getCartProductName(),
                testData.get("bikeLight").asText()
        );
    }

    @Test(description = "Verify user can sort products by name from A to Z")
    public void verifySortProductsByNameAtoZ() {
        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.sortProductsByNameAToZ();
        List<String> actualNames = productPage.getProductNameTexts();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(String::compareTo);
        Assert.assertEquals(actualNames, expectedNames);

    }

    @Test(description = "Verify user can sort products by name from Z to A")
    public void verifySortProductsByNameZtoA() {
        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.sortProductsByNameZToA();
        List<String> actualNames = productPage.getProductNameTexts();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Comparator.reverseOrder());
        Assert.assertEquals(actualNames, expectedNames);
    }


    @Test(description = "Verify user can sort products by price from low to high")
    public void verifySortProductsByPriceLowToHigh() {
        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.sortProductsByPriceLowToHigh();

        List<String> actualPriceTexts = productPage.getProductPriceTexts();

        List<Double> actualPriceValues = new ArrayList<>();

        for (String priceText : actualPriceTexts) {
            double price = Double.parseDouble(priceText.replace("$", ""));
            actualPriceValues.add(price);
        }


        List<Double> expectedPrices = new ArrayList<>(actualPriceValues);
        expectedPrices.sort(Double::compareTo);
        Assert.assertEquals(actualPriceValues, expectedPrices);
    }

    @Test(description = "Verify user can sort products by price from high to low")
    public void verifySortProductsByPriceHighToLow() {
        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.sortProductsByPriceHighToLow();
        List<String> actualPriceTexts = productPage.getProductPriceTexts();

        List<Double> actualPriceValues = new ArrayList<>();

        for (String priceText : actualPriceTexts) {
            Double price = Double.parseDouble(priceText.replace("$", ""));
            actualPriceValues.add(price);
        }

        List<Double> expectedPriceValues = new ArrayList<>(actualPriceValues);
        expectedPriceValues.sort(Comparator.reverseOrder());
        Assert.assertEquals(actualPriceValues,expectedPriceValues);

    }

    @Test(description = "Verify all product prices follow the expected currency format")
    public void verifyAllProductsHaveValidPriceFormat() {


        loginAsValidUser();
        ProductPage productPage = new ProductPage(driver);
        List<String> prices = productPage.getProductPriceTexts();

        for (String price : prices) {
            Assert.assertTrue(
                    price.matches("\\$\\d+\\.\\d{2}"),
                    "Invalid price format: " + price
            );
        }
    }

    @Test(description = "Verify cart badge count updates when a product is added")
    public void verifyCartBadgeUpdatesAfterAddingProduct() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("cartBadgeUpdatesAfterAddingProduct.json");

        loginAsValidUser();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickBikeLight();
        productPage.addToCart();

        String cartBadgeCount = productPage.getCartBadgeCount();

        Assert.assertEquals(
                cartBadgeCount,
                testData.get("expectedCartBadgeCount").asText()
        );
    }
}
