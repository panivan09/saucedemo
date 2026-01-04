package saucedemo.driver.factory;

public class DriverFactoryProvider {

    private DriverFactoryProvider() {}

    public static BrowserDriverFactory getFactory(String browser) {
        return switch (browser) {
            case "chrome" -> new ChromeDriverFactory();
            case "firefox" -> new FirefoxDriverFactory();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser + "!");
        };
    }
}
