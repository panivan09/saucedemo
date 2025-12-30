package saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import saucedemo.model.User;
import saucedemo.page.LoginPage;
import saucedemo.page.ProductPage;
import saucedemo.service.UserCreator;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    protected void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(groups = "smoke", description = "Successful Login should open Products page")
    public void shouldLoginSuccessfullyWithValidUsernameAndPassword(){
        // Given
        User user = UserCreator.standardUser();
        String expected = "Products";

        // When
        loginPage.openPage();
        loginPage.typeUsername(user.username());
        loginPage.typePassword(user.password());
        ProductPage productPage = loginPage.successfulLogin();

        // Then
        Assert.assertEquals(productPage.getPageTitleName(), expected,
                "Product page wasn't opened. Current URL: " + driver.getCurrentUrl());
    }

    @Test(
            groups = "regression",
            dataProvider = "invalidLoginData",
            description = "Unsuccessful Login should show error message"
    )
    public void shouldShowErrorMessageForInvalidUsernameAndPassword(User user, String expectedMessage){
        // When
        loginPage.openPage();
        loginPage.typeUsername(user.username());
        loginPage.typePassword(user.password());
        String actualMessage = loginPage.unsuccessfulLogin();

        // Then
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @DataProvider(name = "invalidLoginData")
    private Object[][] invalidLoginData() {
        return new Object[][]{
                {UserCreator.standardUserWithWrongPassword(), "Epic sadface: Username and password do not match any user in this service"},
                {UserCreator.wrongUserWithCorrectPassword(), "Epic sadface: Username and password do not match any user in this service"},
                {UserCreator.invalidUser(), "Epic sadface: Username and password do not match any user in this service"},
                {UserCreator.withEmptyUsername(), "Epic sadface: Username is required"},
                {UserCreator.withEmptyPassword(), "Epic sadface: Password is required"},
                {UserCreator.withEmptyUsernameAndPassword(), "Epic sadface: Username is required"},
                {UserCreator.lockedOutUser(), "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}