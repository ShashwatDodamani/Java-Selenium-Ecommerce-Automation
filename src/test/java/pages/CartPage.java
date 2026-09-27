package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver){
        super(driver);
    }

    private By cartProductName = By.cssSelector("[data-test='inventory-item-name']");
    private By cartProductPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By cartProductDescription = By.cssSelector("[data-test = 'inventory-item-desc']");
    private By removeButton = By.cssSelector("[data-test^='remove-']");
    private By checkoutButton = By.cssSelector("[data-test='checkout']");
    private By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");



    public String getCartProductName() {
        return getText(cartProductName);
    }

    public String getCartProductPrice() {

        return getText(cartProductPrice);
    }

    public String getCartProductDescription(){
        return getText(cartProductDescription);
    }

    public void removeProduct() {

        click(removeButton);
    }

    public boolean isProductDisplayed() {

        return driver.findElements(cartProductName).size() > 0;
    }

    public void clickCheckout() {

        click(checkoutButton);
    }

    public void clickContinueShopping() {

       click(continueShoppingButton);
    }

    public List<String> getCartProductNameTexts() {
        return getTexts(cartProductName);
    }
}

