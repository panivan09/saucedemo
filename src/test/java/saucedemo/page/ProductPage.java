package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage{

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
    }

    public CartPage openCartPage() {
        clickOnElementWhenClickable(cartButton);

        return new CartPage(driver);
    }

    public String getPageTitleName() {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(pageTitle));

        return pageTitle.getText();
    }

    public void openSortDropdownButtonAndClickOnChosenSortOption(String option) {
        waitUntilElementVisible(sortDropdownButton);
        Select options = new Select(sortDropdownButton);
        options.selectByValue(option);
    }

    public List<String> getItemNames() {
        return itemNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getItemPrices() {
        return itemPrices.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public void addThreeItemsToCart() {
        clickOnElementWhenClickable(addBikeLightToCartButton);
        clickOnElementWhenClickable(addBoltTshirtToCartButton);
        clickOnElementWhenClickable(addLabsOnesieToCartButton);
    }

    public LoginPage clickOnLogoutButtonAndShowLoginPage() {
        clickOnElementWhenClickable(burgerMenuButton);
        clickOnElementWhenClickable(logoutButton);

        return new LoginPage(driver);
    }
}
