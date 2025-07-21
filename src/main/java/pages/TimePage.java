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

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;

    @FindBy(xpath = "//button[normalize-space()='My Timesheet']")
    private WebElement myTimesheetButton;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addTimesheetButton;

    @FindBy(xpath = "//button[normalize-space()='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Approve']")
    private WebElement approveButton;

    @FindBy(xpath = "//button[normalize-space()='Reject']")
    private WebElement rejectButton;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@title='Previous']")
    private WebElement previousButton;

    @FindBy(xpath = "//button[@title='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//h6[contains(text(),'Timesheet for')]")
    private WebElement timesheetHeader;

    // Add more locators as needed for timesheet rows, status, etc.

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

    public void clickResetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public void clickMyTimesheetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(myTimesheetButton)).click();
    }

    public void clickAddTimesheetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addTimesheetButton)).click();
    }

    public void clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickApproveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(approveButton)).click();
    }

    public void clickRejectButton() {
        wait.until(ExpectedConditions.elementToBeClickable(rejectButton)).click();
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void clickPreviousButton() {
        wait.until(ExpectedConditions.elementToBeClickable(previousButton)).click();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public boolean isTimesheetDisplayedFor(String employeeName) {
        String header = wait.until(ExpectedConditions.visibilityOf(timesheetHeader)).getText();
        return header.contains(employeeName);
    }

    public void clickButtonByName(String buttonName) {
        switch (buttonName.toLowerCase()) {
            case "my timesheet":
                clickMyTimesheetButton();
                break;
            case "add":
                clickAddTimesheetButton();
                break;
            case "edit":
                clickEditButton();
                break;
            case "save":
                clickSaveButton();
                break;
            case "approve":
                clickApproveButton();
                break;
            case "reject":
                clickRejectButton();
                break;
            case "submit":
                clickSubmitButton();
                break;
            case "previous":
                clickPreviousButton();
                break;
            case "next":
                clickNextButton();
                break;
            case "reset":
                clickResetButton();
                break;
            case "view":
                clickViewButton();
                break;
            default:
                throw new IllegalArgumentException("Button not recognized: " + buttonName);
        }
    }

    public boolean isMyTimesheetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(timesheetHeader)).isDisplayed();
    }
}