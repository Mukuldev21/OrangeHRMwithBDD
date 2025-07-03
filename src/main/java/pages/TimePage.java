package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TimePage extends BasePage {

    @FindBy(linkText = "Time")
    private WebElement timeMenu;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//button[normalize-space()='View']")
    private WebElement viewButton;

    @FindBy(xpath = "//h6[contains(text(),'Timesheet for')]")
    private WebElement timesheetHeader;

    @FindBy(xpath = "//button[normalize-space()='My Timesheet']")
    private WebElement myTimesheetButton;

    public TimePage(WebDriver driver) {
        super(driver);
    }

    public void goToTimePage() {
        wait.until(ExpectedConditions.elementToBeClickable(timeMenu)).click();
    }

    public boolean isAt() {
        return wait.until(ExpectedConditions.visibilityOf(viewButton)).isDisplayed();
    }

    public void enterEmployeeName(String name) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameField)).clear();
        employeeNameField.sendKeys(name);
    }

    public void clickViewButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewButton)).click();
    }

    public boolean isTimesheetDisplayedFor(String employeeName) {
        String header = wait.until(ExpectedConditions.visibilityOf(timesheetHeader)).getText();
        return header.contains(employeeName);
    }

    public void clickButtonByName(String buttonName) {
        if (buttonName.equalsIgnoreCase("My Timesheet")) {
            wait.until(ExpectedConditions.elementToBeClickable(myTimesheetButton)).click();
        }
        // Add more buttons as needed
    }

    public boolean isMyTimesheetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(timesheetHeader)).isDisplayed();
    }
}