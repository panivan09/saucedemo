package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com";
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
        driver.get(LOGIN_PAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(usernameInput));
    }

    public void typeUsername(String username) {
        waitUntilElementVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void typePassword(String password) {
        waitUntilElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public ProductPage successfulLogin() {
        clickOnLoginButton();

        return new ProductPage(driver);
    }

    public String unsuccessfulLogin() {
        clickOnLoginButton();
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(errorLoginMessage));

        return errorLoginMessage.getText();
    }

    public ProductPage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);

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
    }
}
