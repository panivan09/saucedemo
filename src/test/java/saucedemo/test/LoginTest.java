package saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import saucedemo.page.LoginPage;
import saucedemo.page.ProductPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    protected void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Successful Login should open Products page")
    public void shouldLoginSuccessfullyWithValidUsernameAndPassword(){
        // Given
        String username = "standard_user";
        String password = "secret_sauce";
        String expected = "Products";

        // When
        loginPage.openPage();
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        ProductPage productPage = loginPage.successfulLogin();

        // Then
        Assert.assertEquals(productPage.getPageTitleName(), expected,
                "Product page wasn't opened. Current URL: " + driver.getCurrentUrl());
    }

    @Test(
            dataProvider = "invalidLoginData",
            description = "Unsuccessful Login should show error message"
    )
    public void shouldShowErrorMessageForInvalidUsernameAndPassword(
            String username,
            String password,
            String expectedMessage
    ){
        // When
        loginPage.openPage();
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        String actualMessage = loginPage.unsuccessfulLogin();

        // Then
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @DataProvider(name = "invalidLoginData")
    private Object[][] invalidLoginData() {
        return new Object[][]{
                {"standard_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"},
                {"wrong_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"wrong_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}
