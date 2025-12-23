package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends BasePage{

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

    public void fillUserInformation(String firstName, String lastname, String postalCode) {
        waitUntilElementVisible(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        waitUntilElementVisible(lastNameInput);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastname);

        waitUntilElementVisible(postalCodeInput);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
    }

    public CheckoutStepTwoPage clickOnContinueButtonAndOpenCheckoutStepTwoPage(){
        clickOnElementWhenClickable(continueButton);

        return new CheckoutStepTwoPage(driver);
    }
}
