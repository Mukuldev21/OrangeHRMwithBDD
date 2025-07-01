package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.JavaScriptUtil;

public class PIMPage extends BasePage {

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addEmployeeButton;

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // In PIMPage.java
    // Java
    public void clickAddEmployee() {
        JavaScriptUtil.scrollIntoView(driver, addEmployeeButton);
        try {
            addEmployeeButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addEmployeeButton);
        }
    }

    // Optional: Verify you are on the PIM page
    public boolean isAt() {
        return wait.until(ExpectedConditions.visibilityOf(addEmployeeButton)).isDisplayed();
    }
}