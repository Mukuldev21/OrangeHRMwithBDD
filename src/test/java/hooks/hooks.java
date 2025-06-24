package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigReader;
import utils.ScreenshotUtil;

import java.net.URI;
import java.time.Duration;
import java.util.Properties;

public class hooks {

    public static WebDriver driver;
    public static Properties config;
    public static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> scenarioThread = new ThreadLocal<>();

    @Before
    public void setUp(Scenario scenario) {
        // Load Configuration Properties
        config = ConfigReader.loadProperties("src/test/resources/Config/config.properties");

        // Update last name in JSON before loading it
        try {
            utils.JsonUpdater.updateLastName("src/test/resources/testingData/EmployeeDetails.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConfigReader.loadJsonConfig("src/test/resources/testingData/EmployeeDetails.json");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        ExtentTest test = extent.createTest(scenario.getName());
        String runTime = System.getProperty("run.time", "Not Provided");
        extent.setSystemInfo("Run Time (IST)", runTime);
        scenarioThread.set(test);
        // Clean screenshots older than 1 day before each test run
        ScreenshotCleaner.deleteOldScreenshots("test-output/SparkReport/screenshots", 1);
    }

   // Inside hooks.java

@AfterStep
    public void addScreenshotToReport(Scenario scenario) {
        if (driver == null) {
            driver = new ChromeDriver();
        }

        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(15)).until(
            webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete")
        );

        String stepInfo = StepTracker.getLastStepText();
        if (stepInfo == null) {
            stepInfo = "Step info not found";
        }

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
                scenario.getName().replaceAll(" ", "_") + "_" + System.currentTimeMillis());

        try {
            String imgTag = "<br><img src='" + screenshotPath + "' height='200' width='200'/>";
            String logMessage = "<span style='font-weight:bold; color:#90caf9; background:#263238; padding:2px 6px; border-radius:4px;'>"
                + stepInfo + "</span>" + imgTag;

            if (scenario.isFailed()) {
                String error = StepErrorTracker.getLastError();
                String errorDetails = error != null
                        ? "<br><pre style='color:#ff5252; background:#212121;'>" + error + "</pre>"
                        : "<br><pre style='color:#ff5252; background:#212121;'>Step failed. See stack trace in console or logs.</pre>";
                logMessage += errorDetails;
                scenarioThread.get().fail(logMessage);
                ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, logMessage);
            } else {
                scenarioThread.get().pass(logMessage);
                ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, logMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StepErrorTracker.clear();
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        if (driver != null) {
            driver.quit();
            extent.flush();
            }

        }

}
