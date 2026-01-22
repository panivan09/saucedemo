package saucedemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"saucedemo"},
        plugin = {"pretty"},
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
