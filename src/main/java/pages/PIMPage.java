package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {

    @FindBy(xpath = "//button[text() =' Add ']")
    private WebElement addEmployeeButton;

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // In PIMPage.java
    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
    }

    // Optional: Verify you are on the PIM page
    public boolean isAt() {
        return wait.until(ExpectedConditions.visibilityOf(addEmployeeButton)).isDisplayed();
    }
}