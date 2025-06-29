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
                    // Do NOT set user-data-dir; Chrome will use a temporary directory by default
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