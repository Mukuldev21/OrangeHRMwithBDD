package utils;

  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.chrome.ChromeDriver;
  import org.openqa.selenium.firefox.FirefoxDriver;

  import java.io.IOException;
  import java.util.Properties;
  import org.openqa.selenium.chrome.ChromeOptions;
  import java.nio.file.Files;
  import org.openqa.selenium.firefox.FirefoxDriver;
  import org.openqa.selenium.firefox.FirefoxOptions;
  import java.nio.file.Path;

  public class DriverManager {
      private static WebDriver driver;

      public static WebDriver getDriver(Properties config) {
          if (driver == null) {
              String browser = config.getProperty("browser", "chrome").toLowerCase();
              switch (browser) {
                  case "firefox":
                      driver = new FirefoxDriver();
                      break;
                  case "chrome":
                      ChromeOptions options = new ChromeOptions();
                      // Set a unique user data dir to avoid session conflicts in CI
                      String tempProfile = null;
                      try {
                          tempProfile = Files.createTempDirectory("chrome-profile-").toString();
                      } catch (IOException e) {
                          throw new RuntimeException(e);
                      }
                      options.addArguments("--user-data-dir=" + tempProfile);
                      driver = new ChromeDriver(options);
                      break;
                  default:
                      throw new IllegalArgumentException("Unsupported browser: " + browser);
              }
          }
          return driver;
      }

      public static void quitDriver() {
          if (driver != null) {
              driver.quit();
              driver = null;
          }
      }
  }