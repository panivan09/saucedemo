package saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.page.*;
import saucedemo.service.UserCreator;

import java.util.List;

public class ProductTest extends BaseTest{

    private ProductPage productPage;

    @BeforeMethod(alwaysRun = true)
    protected void initPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        productPage = loginPage.loginAs(UserCreator.standardUser());
    }

    @Test(groups = "regression", description = "Should sort product items alphabetically (Z to A) by name")
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

    @Test(groups = "regression", description = "Should sort product items by price (low to high)")
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

    @Test(groups = "regression", description = "Should sort product items by price (high to low)")
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
}
