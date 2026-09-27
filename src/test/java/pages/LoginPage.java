package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
   public LoginPage(WebDriver driver){
       super(driver);

   }

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By productTitle = By.cssSelector("[data-test = 'title']");
    private By loginErrorMessage = By.cssSelector(("[data-test = 'error']"));


    public void enterUsername(String username) {
        enterText(usernameField,username);
    }

    public void enterPassword(String password){
       enterText(passwordField,password);
    }

    public void clickLogin(){
        click(loginButton);
    }

    public String getProductTitle(){
        return getText(productTitle);
    }

    public String getLoginErrorMessage() {
        return getText(loginErrorMessage);
    }


}
