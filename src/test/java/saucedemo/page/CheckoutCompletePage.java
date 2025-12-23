package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{

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

        return completeHeader.getText();
    }
}
