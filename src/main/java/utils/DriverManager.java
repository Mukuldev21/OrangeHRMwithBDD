package utils;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.firefox.FirefoxDriver;
    import java.util.Properties;

    public class DriverManager {

        public static WebDriver getDriver(Properties config) {
            String browser = config.getProperty("browser", "chrome").toLowerCase();
            switch (browser) {
                case "firefox":
                    return new FirefoxDriver();
                case "chrome":
                    return new ChromeDriver();
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