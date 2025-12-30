package saucedemo.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import saucedemo.driver.DriverManager;
import saucedemo.utils.TestListener;


@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    protected void setUp() {
        driver = DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        DriverManager.closeDriver();
    }
}
