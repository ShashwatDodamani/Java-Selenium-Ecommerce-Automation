package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver){
        super(driver);
    }

    private By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private By bikeLight = By.id("item_0_title_link");
    private By productDetailsTitle = By.cssSelector("[data-test='inventory-item-name']");
    private By addToCartButton = By.id("add-to-cart");
    private By shoppingCart = By.cssSelector("[data-test='shopping-cart-link']");
    private By productDescriptions = By.cssSelector("[data-test='inventory-item-desc']");
    private By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    private By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private By backToProductsButton = By.cssSelector("[data-test='back-to-products']");


    private void selectSortOption(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(sortDropdown)
        );

        Select select = new Select(dropdown);
        select.selectByValue(value);
    }


    public List<String> getProductNameTexts() {
        return getTexts(productNames);
    }

    public List<String> getProductDescriptionTexts() {
        return getTexts(productDescriptions);
    }


    public List<String> getProductPriceTexts() {
        return getTexts(productPrices);
    }


    public void clickBikeLight(){
       click(bikeLight);
    }

    public String productDetailsTitle(){
       return  getText(productDetailsTitle);
    }

    public void addToCart(){
        click(addToCartButton);
    }

    public void clickShoppingCart(){
        click(shoppingCart);
    }



    public void sortProductsByNameAToZ() {
        selectSortOption("az");
    }

    public void sortProductsByNameZToA() {
        selectSortOption("za");
    }

    public void sortProductsByPriceLowToHigh() {
        selectSortOption("lohi");
    }

    public void sortProductsByPriceHighToLow() {
        selectSortOption("hilo");
    }

    public String getCartBadgeCount() {

        return getText(cartBadge);
    }
    public void clickBackToProducts() {

       click(backToProductsButton);
    }

    public void selectProduct(String productName) {
        By product = By.xpath(
                "//a[contains(@id,'title_link')][.//div[@data-test='inventory-item-name' and text()='"
                        + productName + "']]"
        );

        click(product);
    }
}
