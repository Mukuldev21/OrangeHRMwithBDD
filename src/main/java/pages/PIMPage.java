package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.JavaScriptUtil;

public class PIMPage extends BasePage {

    @FindBy(xpath = "//button[text() =' Add ']")
    private WebElement addEmployeeButton;

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // In PIMPage.java
    public void clickAddEmployee() {
        // Wait for any loader/overlay to disappear (adjust selector if needed)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-form-loader, div.oxd-loading-spinner")
            ));
        } catch (Exception ignored) {}

        JavaScriptUtil.scrollIntoView(driver, addEmployeeButton);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Fallback: click with JavaScript if intercepted
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addEmployeeButton);
        }
    }

    // Optional: Verify you are on the PIM page
    public boolean isAt() {
        return wait.until(ExpectedConditions.visibilityOf(addEmployeeButton)).isDisplayed();
    }
}