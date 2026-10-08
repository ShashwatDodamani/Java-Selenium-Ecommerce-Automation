package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private static final String VALID_USERNAME = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";

    @Test(description = "Verify valid user can log in successfully")
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getProductTitle(),
                "Products"
        );
    }

    @Test(description = "Verify login fails with an invalid username and valid password")
    public void invalidUserNameAndValidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @Test(description = "Verify login fails with a valid username and invalid password")
    public void validUserNameAndInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword("invalid_password");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @Test(description = "Verify login fails with an invalid username and invalid password")
    public void invalidUserNameAndInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @Test(description = "Verify validation message is displayed when username is empty")
    public void emptyUserNameAndValidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("");
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Username is required"
        );
    }

    @Test(description = "Verify validation message is displayed when password is empty")
    public void validUserNameAndEmptyPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Password is required"
        );
    }

    @Test(description = "Verify username required message is displayed when both username and password are empty")
    public void emptyUserNameAndEmptyPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Username is required"
        );
    }

    @Test(description = "Verify locked out user cannot log in")
    public void lockedOutUserTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out."
        );
    }
}