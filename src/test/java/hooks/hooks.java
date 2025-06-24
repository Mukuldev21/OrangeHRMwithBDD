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

import java.time.Duration;
import java.util.Properties;

public class hooks {

    public static WebDriver driver;
    public static Properties config;
    public static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> scenarioThread = new ThreadLocal<>();


    @Before
    public void setUp(Scenario scenario) {

        //Load Configuration Properties
        config = ConfigReader.loadProperties("src/test/resources/Config/config.properties");
        ConfigReader.loadJsonConfig("src/test/resources/testingData/RecruitmentDetails.Json");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioThread.set(test);
        FeatureParser.loadFeatureLines(scenario.getUri());
        //LoginPage loginPage = new LoginPage(driver);
    }

    @AfterStep
    public void addScreenshotToReport(Scenario scenario) {

        if (driver == null) {
            driver = new ChromeDriver();
        }

        String stepInfo = FeatureParser.getStepInfo(scenario.getLine());
        if (stepInfo == null) stepInfo = "Step info not found";

        // Capture screenshot
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
                scenario.getName().replaceAll(" ", "_") + "_" + System.currentTimeMillis());

        try {
            //Media media = MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build();
            String imgTag = "<br><img src='" + screenshotPath + "' height='200' width='200'/>";

            if (scenario.isFailed()) {
                scenarioThread.get().fail("Step failed: " + stepInfo + imgTag);
                ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,
                        "Step failed: " + stepInfo + imgTag);
            } else {
                scenarioThread.get().pass("Step passed: " + stepInfo + imgTag);
                ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,
                        "Step passed: " + stepInfo + imgTag);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
