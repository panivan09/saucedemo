package saucedemo.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CartPage.class);

    private static final String CART_INVENTORY_ITEM_NAME_LOCATOR = "[data-test='inventory-item-name']";

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = "[data-test='inventory-item']")
    private List<WebElement> cartItems;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    private WebElement removeBoltTshirtButton;

    @FindBy(id = "remove-sauce-labs-onesie")
    private WebElement removeLabsOnesieButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        throw new UnsupportedOperationException("CartPage can't be opened directly. Login first.");
    }

    public CheckoutStepOnePage clickOnCheckoutButtonAndOpenCheckoutStepOnePage() {
        clickOnElementWhenClickable(checkoutButton);
        logger.info("Open Checkout Step One page");

        return new CheckoutStepOnePage(driver);
    }

    public int getNumberOfCartItems() {
        int size = cartItems.size();
        logger.debug("Cart items count: {}", size);

        return size;
    }

    public List<String> cartItemNames() {
        List<String> names = cartItems.stream()
                .map(item -> item.findElement(By.cssSelector(CART_INVENTORY_ITEM_NAME_LOCATOR)).getText())
                .toList();
        logger.debug("Cart item names: {}", names);

        return names;
    }

    public void removeTwoItemsFromCart() {
        clickOnElementWhenClickable(removeBoltTshirtButton);
        clickOnElementWhenClickable(removeLabsOnesieButton);
        logger.debug("Remove two items: {}, {}", "Bolt Tshirt", "Labs Onesie");
    }

    public ProductPage clickOnContinueShoppingButtonAndShowProductPage() {
        clickOnElementWhenClickable(continueShoppingButton);
        logger.info("Click continue shopping and open Product page");

        return new ProductPage(driver);
    }
}
