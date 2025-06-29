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
      //private static WebDriver driver;

      public static WebDriver getDriver(Properties config) {
          String browser = config.getProperty("browser", "chrome").toLowerCase();
          switch (browser) {
              case "firefox":
                  FirefoxOptions firefoxOptions = new FirefoxOptions();
                  try {
                      Path firefoxProfile = Files.createTempDirectory("firefox-profile-");
                      firefoxOptions.addArguments("-profile", firefoxProfile.toString());
                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  }
                  return new FirefoxDriver(firefoxOptions);
              case "chrome":
                  ChromeOptions chromeOptions = new ChromeOptions();
                  try {
                      Path chromeProfile = Files.createTempDirectory("chrome-profile-");
                      chromeOptions.addArguments("--user-data-dir=" + chromeProfile.toString());
                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  }
                  return new ChromeDriver(chromeOptions);
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