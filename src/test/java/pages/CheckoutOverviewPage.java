package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By productDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private By paymentInformation = By.cssSelector("[data-test='payment-info-value']");
    private By shippingInformation = By.cssSelector("[data-test='shipping-info-value']");
    private By itemTotal = By.cssSelector("[data-test='subtotal-label']");
    private By tax = By.cssSelector("[data-test='tax-label']");
    private By total = By.cssSelector("[data-test='total-label']");
    private By cancelButton = By.cssSelector("[data-test='cancel']");
    private By finishButton = By.id("finish");


    public String getProductName() {

        return getText(productName);
    }

    public String getProductPrice() {

        return getText(productPrice);
    }

    public String getProductDescription() {

        return getText(productDescription);
    }

    public String getPaymentInformation() {

        return getText(paymentInformation);
    }

    public String getShippingInformation() {

        return getText(shippingInformation);
    }

    public String getItemTotal() {

        return getText(itemTotal);
    }

    public String getTax() {

        return getText(tax);
    }

    public String getTotal() {

        return getText(total);
    }

    public void clickFinish() {

        click(finishButton);
    }

    public void clickCancel() {
        click(cancelButton);
    }

}