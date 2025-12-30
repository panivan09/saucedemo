package saucedemo.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemo.model.User;
import saucedemo.service.TestDataReader;

import java.time.Duration;

public class LoginPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private static final String BASE_URL = "testdata.baseUrl";
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private static final String LOGIN_BUTTON_VALUE = "value";

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorLoginMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.get(TestDataReader.getTestData(BASE_URL));
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(usernameInput));
        logger.info("Login page opened: {}", TestDataReader.getTestData(BASE_URL));
    }

    public void typeUsername(String username) {
        waitUntilElementVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        logger.info("Type username");
        logger.debug("Username value: {}", username);
    }

    public void typePassword(String password) {
        waitUntilElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        logger.info("Type password");
        logger.debug("Password value: [HIDDEN]");
    }

    public ProductPage successfulLogin() {
        clickOnLoginButton();
        logger.info("Product page opened");

        return new ProductPage(driver);
    }

    public String unsuccessfulLogin() {
        clickOnLoginButton();
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(errorLoginMessage));
        String message = errorLoginMessage.getText();
        logger.warn("Login failed. Message: {}", message);

        return message;
    }

    public ProductPage loginAs(User user) {
        typeUsername(user.username());
        typePassword(user.password());
        logger.info("Login as user: {}", user.username());

        return successfulLogin();
    }

    public String getTextLoginButton() {
        waitUntilElementVisible(loginButton);

        return loginButton.getAttribute(LOGIN_BUTTON_VALUE);
    }

    private void clickOnLoginButton() {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        logger.info("Click Login button");
    }
}
