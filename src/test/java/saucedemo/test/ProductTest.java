package saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.page.*;

import java.util.List;

public class ProductTest extends BaseTest{

    private static final String STANDARD_USER = "standard_user";
    private static final String PASSWORD_FOR_STANDARD_USER = "secret_sauce";

    private ProductPage productPage;

    @BeforeMethod
    protected void initPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        productPage = loginPage.loginAs(STANDARD_USER, PASSWORD_FOR_STANDARD_USER);
    }

    @Test(description = "Add item to cart, checkout, and complete order")
    public void shouldAddSingleItemToCartAndCompletePurchaseSuccessfully() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        String postCode = "12345";
        String expected = "Thank you for your order!";

        // When
        productPage.addBackpackToCart();
        CartPage cartPage = productPage.openCartPage();
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickOnCheckoutButtonAndOpenCheckoutStepOnePage();
        checkoutStepOnePage.fillUserInformation(firstName, lastName, postCode);
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.clickOnContinueButtonAndOpenCheckoutStepTwoPage();
        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.clickOnFinishButtonAndOpenCheckoutCompletePage();
        String actual = checkoutCompletePage.getCompletedHeaderText();

        // Then
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Should sort product items alphabetically (A to Z) by name")
    public void shouldSortProductsByNameFromAToZ() {
        // Given
        String sortOption = "az";
        List<String> expected = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );

        // When
        productPage.openSortDropdownButtonAndClickOnChosenSortOption(sortOption);
        List<String> actual = productPage.getItemNames();

        // Then
        Assert.assertEquals(actual, expected, "Items are not sorted A to Z");
    }

    @Test(description = "Should sort product items alphabetically (Z to A) by name")
    public void shouldSortProductsByNameFromZToA() {
        // Given
        String sortOption = "za";
        List<String> expected = List.of(
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Onesie",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Bike Light",
                "Sauce Labs Backpack"
        );

        // When
        productPage.openSortDropdownButtonAndClickOnChosenSortOption(sortOption);
        List<String> actual = productPage.getItemNames();

        // Then
        Assert.assertEquals(actual, expected, "Items are not sorted Z to A");
    }

    @Test(description = "Should sort product items by price (low to high)")
    public void shouldSortProductsByPriceLowToHigh() {
        // Given
        String sortOption = "lohi";
        List<Double> expected = List.of(7.99, 9.99, 15.99, 15.99, 29.99, 49.99);

        // When
        productPage.openSortDropdownButtonAndClickOnChosenSortOption(sortOption);
        List<Double> actual = productPage.getItemPrices();

        // Then
        Assert.assertEquals(actual, expected, "Prices are not sorted low to high");
    }

    @Test(description = "Should sort product items by price (high to low)")
    public void shouldSortProductsByPriceHighToLow() {
        // Given
        String sortOption = "hilo";
        List<Double> expected = List.of(49.99, 29.99, 15.99, 15.99, 9.99, 7.99);

        // When
        productPage.openSortDropdownButtonAndClickOnChosenSortOption(sortOption);
        List<Double> actual = productPage.getItemPrices();

        // Then
        Assert.assertEquals(actual, expected, "Prices are not sorted high to low");
    }

    @Test(description = "Logout should return user to Login page")
    public void shouldLogoutSuccessfully() {
        // Given
        String expected = "Login";

        // When
        LoginPage loginPage = productPage.clickOnLogoutButtonAndShowLoginPage();
        String actual = loginPage.getTextLoginButton();

        // Then
        Assert.assertEquals(actual, expected);
    }
}
