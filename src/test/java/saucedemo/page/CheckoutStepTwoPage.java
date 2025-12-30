package saucedemo.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CheckoutStepTwoPage.class);

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
        logger.info("Click continue on Checkout Step One and open Checkout Step Two page");

        return new CheckoutCompletePage(driver);
    }
}
