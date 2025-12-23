package saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.page.CartPage;
import saucedemo.page.LoginPage;
import saucedemo.page.ProductPage;

import java.util.List;

public class CartTest extends BaseTest{

    private static final String STANDARD_USER = "standard_user";
    private static final String PASSWORD_FOR_STANDARD_USER = "secret_sauce";

    private ProductPage productPage;

    @BeforeMethod
    protected void initPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        productPage = loginPage.loginAs(STANDARD_USER, PASSWORD_FOR_STANDARD_USER);
    }

    @Test(description = "Cart should keep 1 item after removing 2 of 3 and return to ProductsPage on Continue Shopping")
    public void shouldRemoveTwoItemsFromCartAndContinueShopping() {
        // Given
        int expectedNumberOfCartItem = 1;
        String expectedCartItemName = "Sauce Labs Bike Light";
        String expectedPageTitleName = "Products";

        // When
        productPage.addThreeItemsToCart();
        CartPage cartPage = productPage.openCartPage();
        cartPage.removeTwoItemsFromCart();
        int actualNumberOfCartItems = cartPage.getNumberOfCartItems();
        List<String> actualCartItemNames = cartPage.cartItemNames();
        ProductPage returnedProductPage = cartPage.clickOnContinueShoppingButtonAndShowProductPage();
        String actualPageTitleName = returnedProductPage.getPageTitleName();

        // Then
        Assert.assertEquals(actualNumberOfCartItems, expectedNumberOfCartItem);
        Assert.assertTrue(actualCartItemNames.contains(expectedCartItemName));
        Assert.assertEquals(actualPageTitleName, expectedPageTitleName);
    }
}
