package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage{

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        throw new UnsupportedOperationException("CheckoutStepTwoPage can't be opened directly. Login first.");
    }

    public CheckoutCompletePage clickOnFinishButtonAndOpenCheckoutCompletePage() {
        clickOnElementWhenClickable(finishButton);

        return new CheckoutCompletePage(driver);
    }
}
