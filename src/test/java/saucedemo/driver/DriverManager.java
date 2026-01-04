package saucedemo.driver;

import org.openqa.selenium.WebDriver;
import saucedemo.driver.factory.DriverFactoryProvider;


public final class DriverManager {

    private static final String DEFAULT_BROWSER = "chrome";
    private static final String BROWSER = "browser";
    private static WebDriver driver;

    private DriverManager() {}

    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty(BROWSER, DEFAULT_BROWSER).trim().toLowerCase();

            WebDriver newDriver = DriverFactoryProvider.getFactory(browser).createDriver();

            driver = DriverDecorator.decorate(newDriver);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
