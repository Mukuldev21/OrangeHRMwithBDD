package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"stepDefinitions","hooks"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,
        tags = "@ForgotPassword"
)
public class testRunner extends AbstractTestNGCucumberTests {

}
