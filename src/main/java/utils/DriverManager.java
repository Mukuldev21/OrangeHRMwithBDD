package utils;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.openqa.selenium.firefox.FirefoxDriver;

    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Properties;

    public class DriverManager {

        public static WebDriver getDriver(Properties config) {
            String browser = config.getProperty("browser", "chrome").toLowerCase();
            switch (browser) {
                case "firefox":
                    return new FirefoxDriver();
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    return new ChromeDriver(options);
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        public static void quitDriver(WebDriver driver) {
            if (driver != null) {
                driver.quit();
            }
        }
    }