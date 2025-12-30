package saucedemo.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

    private final boolean highlight = Boolean.parseBoolean(System.getProperty("highlight", "true"));

    @Override
    public void beforeClick(WebElement element) {
        highlight(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        highlight(element);
    }

    private void highlight(WebElement element) {
        if (!highlight) {
            return;
        }

        try {
            WebDriver driver = (WebDriver) ((WrapsDriver) element).getWrappedDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String original = element.getAttribute("style");
            js.executeScript(
                    "arguments[0].setAttribute('data-orig', arguments[1]);" +
                            "arguments[0].setAttribute('style', arguments[1] + '; border: 3px solid red; background: #fff3cd;');",
                    element,
                    original == null ? "" : original
            );

            Thread.sleep(120);

            js.executeScript(
                    "var o = arguments[0].getAttribute('data-orig');" +
                            "arguments[0].setAttribute('style', o);" +
                            "arguments[0].removeAttribute('data-orig');",
                    element
            );
        } catch (Exception ignored) {}
    }
}