package saucedemo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import saucedemo.driver.DriverManager;
import saucedemo.model.User;
import saucedemo.model.UserInformation;
import saucedemo.page.*;
import saucedemo.service.UserCreator;
import saucedemo.service.UserInformationCreator;

public class CheckoutSteps {

    LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsAStandardUser() {
        WebDriver driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.openPage();

        User standardUser = UserCreator.standardUser();
        productPage = loginPage.loginAs(standardUser);
    }

    @When("I add backpack to cart")
    public void iAddBackpackToCart() {
        productPage.addBackpackToCart();
    }

    @And("I open cart")
    public void iOpenCart() {
        cartPage = productPage.openCartPage();
    }

    @And("I proceed to checkout")
    public void iProceedToCheckout() {
        checkoutStepOnePage = cartPage.clickOnCheckoutButtonAndOpenCheckoutStepOnePage();
    }

    @And("I fill checkout information with valid user data")
    public void iFillCheckoutInformationWithValidUserInformationData() {
        UserInformation userInformation = UserInformationCreator.validUserInformation();
        checkoutStepOnePage.fillUserInformation(userInformation);
        checkoutStepTwoPage = checkoutStepOnePage.clickOnContinueButtonAndOpenCheckoutStepTwoPage();
    }

    @And("I finish checkout")
    public void iFinishCheckout() {
        checkoutCompletePage = checkoutStepTwoPage.clickOnFinishButtonAndOpenCheckoutCompletePage();
    }

    @Then("^I should see checkout complete message \"([^\"]+)\"$")
    public void iShouldSeeCheckoutCompleteMessage(String expected) {
        String actual = checkoutCompletePage.getCompletedHeaderText();
        Assert.assertEquals(actual, expected);
    }

    @When("I logout from Products page")
    public void iLogoutFromProductsPage() {
        loginPage = productPage.clickOnLogoutButtonAndShowLoginPage();
    }

    @Then("Login page should be opened")
    public void loginPageShouldBeOpened() {
        String expected = "Login";
        String actual = loginPage.getTextLoginButton();
        Assert.assertEquals(actual, expected);
    }

}
