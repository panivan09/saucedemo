package saucedemo.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import saucedemo.driver.DriverManager;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    private static final Path SCREENSHOTS_DIR = Paths.get("target", "screenshots");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
    private static final String PNG_SCREENSHOT_FORMAT = ".png";

    @Before
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                WebDriver driver = DriverManager.getDriver();

                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", "Failure screenshot");

                Files.createDirectories(SCREENSHOTS_DIR);
                String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9._-]", "_");
                Path targetFile = SCREENSHOTS_DIR.resolve(
                        safeName + "_" + LocalDateTime.now().format(DATE_FORMAT) + PNG_SCREENSHOT_FORMAT
                );
                Files.write(targetFile, png, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                logger.error("SCENARIO FAILED: {} | Screenshot saved: {}", scenario.getName(), targetFile.toAbsolutePath());
            }
        } catch (Exception e) {
            logger.error("Scenario failed but screenshot was NOT saved: {}", scenario.getName(), e);
        } finally {
            DriverManager.closeDriver();
        }
    }
}