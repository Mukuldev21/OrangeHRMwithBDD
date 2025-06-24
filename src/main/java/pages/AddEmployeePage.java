package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class AddEmployeePage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;

    // Add the missing fields with appropriate locators
    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    @FindBy(xpath = "//span[text()='Employee Id already exists']")
    private WebElement duplicateIdErrorElement;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void verifyPersonalDetailsPage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(personalDetailsHeader)).isDisplayed(),
                "Personal Details page is not displayed");
    }

    public String getEmployeeId() {
        return wait.until(ExpectedConditions.visibilityOf(employeeIdField)).getAttribute("value");
    }

    public void enterEmployeeId(String id) {
        wait.until(ExpectedConditions.visibilityOf(employeeIdField)).clear();
        employeeIdField.sendKeys(id);
    }
    /**
     * Clears the Employee ID field using Actions to ensure it works across different browsers.
     */

    public void clearEmployeeIdField() {
        employeeIdField.click(); // Focus the field

        // Use Actions to select all and delete
        Actions actions = new Actions(driver);
        actions.moveToElement(employeeIdField)
               .click()
               .keyDown(Keys.CONTROL)
               .sendKeys("a")
               .keyUp(Keys.CONTROL)
               .sendKeys(Keys.DELETE)
               .perform();

        // As a fallback, use JavaScript if still not empty
        if (!employeeIdField.getAttribute("value").isEmpty()) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].value='';", employeeIdField);
        }
    }

    public boolean isDuplicateEmployeeIdErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(duplicateIdErrorElement)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}