package saucedemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import saucedemo.driver.DriverManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static final Path SCREENSHOTS_DIR = Paths.get("target", "screenshots");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
    private static final String PNG_SCREENSHOT_FORMAT = ".png";

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot(iTestResult);
    }

    private void saveScreenshot(ITestResult iTestResult) {
        String testName = iTestResult.getTestClass().getName() + "." + iTestResult.getMethod().getMethodName();

        try {
            Files.createDirectories(SCREENSHOTS_DIR);

            String safeName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path targetFile = SCREENSHOTS_DIR.resolve(
                    safeName + "_" + LocalDateTime.now().format(DATE_FORMAT) + PNG_SCREENSHOT_FORMAT
            );

            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), targetFile, StandardCopyOption.REPLACE_EXISTING);

            logger.error("TEST FAILED: {} | Screenshot saved: {}", testName, targetFile.toAbsolutePath());

        } catch (Exception e) {
            logger.error("TEST FAILED: {} | Screenshot was NOT saved.", testName, e);
        }
    }
}
