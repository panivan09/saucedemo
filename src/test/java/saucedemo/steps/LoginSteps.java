package saucedemo.steps;

import io.cucumber.java.en.And;
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

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @When("I login as a standard user")
    public void iLoginAsAStandardUser() {
        User standardUser = UserCreator.standardUser();
        productPage = loginPage.loginAs(standardUser);
    }

    @Then("Products page should be opened")
    public void productsPageShouldBeOpened() {
        String expected = "Products";

        Assert.assertEquals(
                productPage.getPageTitleName(),
                expected,
                "Product page wasn't opened. Current URL: " + driver.getCurrentUrl()
        );
    }

    @When("I am login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
    }

    @Then("I should see login error message {string}")
    public void iShouldSeeLoginErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.unsuccessfulLogin();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        productPage = loginPage.successfulLogin();
    }
}
