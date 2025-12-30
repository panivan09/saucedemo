package saucedemo.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CheckoutCompletePage.class);

    @FindBy(css = "[data-test='complete-header']")
    private WebElement completeHeader;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        throw new UnsupportedOperationException("CheckoutCompletePage can't be opened directly. Login first.");
    }

    public String getCompletedHeaderText() {
        waitUntilElementVisible(completeHeader);
        String message = completeHeader.getText();
        logger.debug("Completed checkout message: {}", message);

        return message;
    }
}
