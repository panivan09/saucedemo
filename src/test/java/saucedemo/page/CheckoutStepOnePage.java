package saucedemo.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import saucedemo.model.UserInformation;

public class CheckoutStepOnePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CheckoutStepOnePage.class);

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        throw new UnsupportedOperationException("CheckoutStepOnePage can't be opened directly. Login first.");
    }

    public void fillUserInformation(UserInformation userInformation) {
        logger.info("Fill Checkout Step One user information");

        waitUntilElementVisible(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(userInformation.firstName());
        logger.debug("First name: {}", userInformation.firstName());

        waitUntilElementVisible(lastNameInput);
        lastNameInput.clear();
        lastNameInput.sendKeys(userInformation.lastName());
        logger.debug("Last name: {}", userInformation.lastName());

        waitUntilElementVisible(postalCodeInput);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(userInformation.postalCode());
        logger.debug("Postal code: {}", userInformation.postalCode());
    }

    public CheckoutStepTwoPage clickOnContinueButtonAndOpenCheckoutStepTwoPage(){
        clickOnElementWhenClickable(continueButton);
        logger.info("Click continue on Checkout Step One and open Checkout Step Two page");

        return new CheckoutStepTwoPage(driver);
    }
}
