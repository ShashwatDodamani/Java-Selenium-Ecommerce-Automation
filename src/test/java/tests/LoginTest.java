package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.JsonDataReader;


public class LoginTest extends BaseTest {


    @Test(description = "Verify valid user can log in successfully")
    public void validLoginTest(){
        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("validLoginTest.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getProductTitle(),testData.get("expectedProductTitle").asText()
        );

    }

    @Test(description = "Verify login fails with an invalid username and valid password")
    public void invalidUserNameAndValidPassword(){

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("invalidUserNameAndValidPassword.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

    @Test(description = "Verify login fails with a valid username and invalid password")
    public void validUserNameAndInvalidPassword(){

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("validUserNameAndInvalidPassword.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

    @Test(description = "Verify login fails with an invalid username and invalid password")
    public void invalidUserNameAndInvalidPassword(){

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("invalidUserNameAndInvalidPassword.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

    @Test(description = "Verify validation message is displayed when username is empty")
    public void emptyUserNameAndValidPassword(){

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("emptyUserNameAndValidPassword.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

    @Test(description = "Verify validation message is displayed when password is empty")
    public void validUserNameAndEmptyPassword(){

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("validUserNameAndEmptyPassword.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

    @Test(description = "Verify username required message is displayed when both username and password are empty")
    public void emptyUserNameAndEmptyPassword(){

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("emptyUserNameAndEmptyPassword.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

    @Test(description = "Verify locked out user cannot log in")
    public void lockedOutUserTest() {

        JsonDataReader reader = new JsonDataReader();
        JsonNode testData = reader.readJson("lockedOutUserTest.json");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(
                testData.get("username").asText()
        );
        loginPage.enterPassword(
                testData.get("password").asText()
        );
        loginPage.clickLogin();
        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                testData.get("expectedErrorMessage").asText()
        );
    }

}
