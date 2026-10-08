package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginStepDefinitions extends BaseTest {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {

        loginPage = new LoginPage(driver);
    }

    @When("the user enters valid username and password")
    public void theUserEntersValidUsernameAndPassword() {

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(
            String username,
            String password) {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {

        loginPage.clickLogin();
    }

    @Then("the Products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {

        Assert.assertEquals(
                loginPage.getProductTitle(),
                "Products"
        );
    }

    @Then("the login error message {string} should be displayed")
    public void theLoginErrorMessageShouldBeDisplayed(
            String expectedMessage) {

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                expectedMessage
        );
    }
}