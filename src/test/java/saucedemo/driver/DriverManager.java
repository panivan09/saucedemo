package saucedemo.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import saucedemo.utils.HighlightListener;

import java.util.Map;

public final class DriverManager {

    private static WebDriver driver;

    private static WebDriver decorate(WebDriver rawDriver) {
        return new EventFiringDecorator(new HighlightListener()).decorate(rawDriver);
    }

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").trim().toLowerCase();

            switch (browser) {
                case "chrome" -> driver = createChromeDriver();
                case "firefox" -> driver = createFirefoxDriver();
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser + "!");
            }
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

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        return decorate(new ChromeDriver(options));
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        return decorate(new FirefoxDriver(options));
    }
}
