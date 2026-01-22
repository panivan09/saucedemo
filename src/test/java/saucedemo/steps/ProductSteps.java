package saucedemo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import saucedemo.driver.DriverManager;
import saucedemo.model.User;
import saucedemo.page.LoginPage;
import saucedemo.page.ProductPage;
import saucedemo.service.UserCreator;

import java.util.List;

public class ProductSteps {

    private ProductPage productPage;

    @Given("I am logged in as standard user")
    public void iAmLoggedInAsStandardUser() {
        WebDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();

        User standardUser = UserCreator.standardUser();
        productPage = loginPage.loginAs(standardUser);
    }

    @When("^I sort products by \"(az)\"$")
    public void iSortProductsBy(String option) {
        productPage.openSortDropdownButtonAndClickOnChosenSortOption(option);
    }

    @Then("products should be sorted A to Z")
    public void productsShouldBeSortedAToZ() {
        List<String> expected = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );

        List<String> actual = productPage.getItemNames();
        Assert.assertEquals(actual, expected, "Items are not sorted A to Z");
    }
}
