package saucedemo.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);

    @FindBy(css = "[data-test='title']")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCartButton;

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartButton;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdownButton;

    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> itemNames;

    @FindBy(css = "[data-test='inventory-item-price']")
    private List<WebElement> itemPrices;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addBoltTshirtToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement addLabsOnesieToCartButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        throw new UnsupportedOperationException("ProductPage can't be opened directly. Login first.");
    }

    public void addBackpackToCart() {
        clickOnElementWhenClickable(addBackpackToCartButton);
        logger.info("Add item to cart: Sauce Labs Backpack");
    }

    public CartPage openCartPage() {
        clickOnElementWhenClickable(cartButton);
        logger.info("Open Cart page");

        return new CartPage(driver);
    }

    public String getPageTitleName() {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(pageTitle));
        String title = pageTitle.getText();
        logger.debug("Products page title: {}", title);

        return title;
    }

    public void openSortDropdownButtonAndClickOnChosenSortOption(String option) {
        waitUntilElementVisible(sortDropdownButton);
        Select options = new Select(sortDropdownButton);
        options.selectByValue(option);
        logger.info("Choose sort option: {} in sort menu", option);
    }

    public List<String> getItemNames() {
        List<String> names = itemNames.stream()
                .map(WebElement::getText)
                .toList();
        logger.debug("Items names count: {}", names.size());

        return names;
    }

    public List<Double> getItemPrices() {
        List<Double> prices = itemPrices.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
        logger.debug("Items prices count: {}", prices.size());

        return prices;
    }

    public void addThreeItemsToCart() {
        clickOnElementWhenClickable(addBikeLightToCartButton);
        clickOnElementWhenClickable(addBoltTshirtToCartButton);
        clickOnElementWhenClickable(addLabsOnesieToCartButton);
        logger.debug("Added three items to Cart: {}, {}, {}", "Bike Light", "Bolt Tshirt", "Labs Onesie");
    }

    public LoginPage clickOnLogoutButtonAndShowLoginPage() {
        clickOnElementWhenClickable(burgerMenuButton);
        logger.debug("Burger menu opened");
        clickOnElementWhenClickable(logoutButton);
        logger.info("Logout clicked and Login page opened");

        return new LoginPage(driver);
    }
}
