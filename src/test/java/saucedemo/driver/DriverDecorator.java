package saucedemo.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import saucedemo.utils.HighlightListener;

public final class DriverDecorator {

    private DriverDecorator() {}

    public static WebDriver decorate(WebDriver driver) {
        return new EventFiringDecorator(new HighlightListener()).decorate(driver);
    }
}
