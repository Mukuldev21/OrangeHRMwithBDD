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
                    // Create a unique temp directory for Chrome's user data
                    try {
                        Path tempProfile = Files.createTempDirectory("chrome-profile-");
                        options.addArguments("user-data-dir=" + tempProfile.toAbsolutePath().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Optionally, you can continue with default options if temp dir fails
                    }
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