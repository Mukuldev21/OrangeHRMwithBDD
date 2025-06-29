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
                      ChromeOptions chromeOptions = new ChromeOptions();
                      try {
                          Path chromeProfile = Files.createTempDirectory("chrome-profile-");
                          chromeOptions.addArguments("--user-data-dir=" + chromeProfile.toString());
                      } catch (IOException e) {
                          throw new RuntimeException(e);
                      }
                      driver = new ChromeDriver(chromeOptions);
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