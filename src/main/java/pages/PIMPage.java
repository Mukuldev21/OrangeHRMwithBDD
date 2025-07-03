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

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameSearchField;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
    private java.util.List<WebElement> searchResultRows;

    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement employeeIdSearchField;

    @FindBy(xpath = "//label[text()='Job Title']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
    private WebElement jobTitleDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> jobTitleOptions;

    @FindBy(xpath = "//span[contains(text(),'No Records Found')]")
    private WebElement noRecordsFoundMessage;

    @FindBy(xpath = "//label[text()='Employment Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
    private WebElement employmentStatusDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> employmentStatusOptions;

    @FindBy(xpath = "//label[text()='Supervisor Name']/../following-sibling::div//input")
    private WebElement supervisorNameSearchField;

    @FindBy(xpath = "//label[text()='Sub Unit']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
    private WebElement subUnitDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> subUnitOptions;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;

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

    public void enterEmployeeNameInSearchField(String name) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameSearchField)).clear();
        employeeNameSearchField.sendKeys(name);
        // Optionally, wait for suggestions and select if needed
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean isEmployeeNameInSearchResults(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        String[] nameParts = name.trim().split("\\s+", 2);
        String firstName = nameParts.length > 0 ? nameParts[0] : "";
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        for (WebElement row : searchResultRows) {
            // Scroll row into view
            JavaScriptUtil.scrollIntoView(driver, row);
            String rowText = row.getText();
            if (rowText.contains(firstName) && rowText.contains(lastName)) {
                return true;
            }
        }
        return false;
    }


    // --- Enter Employee ID in Search Field ---
    public void enterEmployeeIdInSearchField(String id) {
        wait.until(ExpectedConditions.visibilityOf(employeeIdSearchField)).clear();
        employeeIdSearchField.sendKeys(id);
    }

    // --- Check if Employee ID is in Search Results ---
    public boolean isEmployeeIdInSearchResults(String id) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        for (WebElement row : searchResultRows) {
            if (row.getText().contains(id)) {
                return true;
            }
        }
        return false;
    }

    // --- Select Job Title from Dropdown ---
    public void selectJobTitleFromDropdown(String jobTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(jobTitleOptions));
        for (WebElement option : jobTitleOptions) {
            if (option.getText().equalsIgnoreCase(jobTitle)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Job title '" + jobTitle + "' not found in dropdown options.");
    }

    // --- Check if Job Title is in Search Results ---
    public boolean isJobTitleInSearchResults(String jobTitle) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        for (WebElement row : searchResultRows) {
            if (row.getText().contains(jobTitle)) {
                return true;
            }
        }
        return false;
    }

    /*public String getNoRecordFoundMessage() {
        // Update the locator as per your actual error message element
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOf(noRecordsFoundMessage));
        return errorMsg.getText();
    }*/

    // In PIMPage.java
    public String getFloatingErrorMessage() {
        // Adjust the XPath to match your floating message
        By toastLocator = By.xpath("//div[contains(@class,'oxd-toast-content') or contains(text(),'No Records Found')]");
        WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));
        return toast.getText();
    }

    public String getFloatingErrorParameterMessage() {
        // Adjust the XPath to match your floating message
        By toastLocator = By.xpath("//div[contains(@class,'oxd-toast-content') or contains(text(),'Invalid Parameter')]");
        WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));
        return toast.getText();
    }

    public void selectEmploymentStatusFromDropdown(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(employmentStatusDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(employmentStatusOptions));
        for (WebElement option : employmentStatusOptions) {
            if (option.getText().equalsIgnoreCase(status)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Employment status '" + status + "' not found in dropdown options.");
    }

    public boolean isEmploymentStatusInSearchResults(String status) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        for (WebElement row : searchResultRows) {
            if (row.getText().contains(status)) {
                return true;
            }
        }
        return false;
    }

    // Method to enter supervisor name
    public void enterSupervisorNameInSearchField(String supervisorName) {
        wait.until(ExpectedConditions.visibilityOf(supervisorNameSearchField)).clear();
        supervisorNameSearchField.sendKeys(supervisorName);
    }

    // Method to check if supervisor name is in search results
    public boolean isSupervisorNameInSearchResults(String supervisorName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        for (WebElement row : searchResultRows) {
            if (row.getText().toLowerCase().contains(supervisorName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // Method to select sub unit
    public void selectSubUnitFromDropdown(String subUnit) {
        wait.until(ExpectedConditions.elementToBeClickable(subUnitDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(subUnitOptions));
        for (WebElement option : subUnitOptions) {
            if (option.getText().equalsIgnoreCase(subUnit)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Sub Unit '" + subUnit + "' not found in dropdown options.");
    }

    // Check if both job title and sub unit are in search results
    public boolean isJobTitleAndSubUnitInSearchResults(String jobTitle, String subUnit) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        for (WebElement row : searchResultRows) {
            String rowText = row.getText();
            if (rowText.contains(jobTitle) && rowText.contains(subUnit)) {
                return true;
            }
        }
        return false;
    }

    // Check if "No Results Found" is shown in dropdown
    public boolean isNoResultsFoundInDropdown() {
        return !driver.findElements(By.xpath("//span[text()='No Results Found']")).isEmpty();
    }

    public void clickResetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    // Method to check if all search fields are cleared
    public boolean areAllSearchFieldsCleared() {
        boolean isEmployeeNameEmpty = employeeNameSearchField.getAttribute("value").isEmpty();
        boolean isEmployeeIdEmpty = employeeIdSearchField.getAttribute("value").isEmpty();
        // For dropdowns, check if they are reset to default (usually "-- Select --" or empty)
        boolean isJobTitleDefault = jobTitleDropdown.getText().trim().equals("-- Select --") || jobTitleDropdown.getText().trim().isEmpty();
        boolean isEmploymentStatusDefault = employmentStatusDropdown.getText().trim().equals("-- Select --") || employmentStatusDropdown.getText().trim().isEmpty();
        boolean isSupervisorNameEmpty = supervisorNameSearchField.getAttribute("value").isEmpty();
        boolean isSubUnitDefault = subUnitDropdown.getText().trim().equals("-- Select --") || subUnitDropdown.getText().trim().isEmpty();

        return isEmployeeNameEmpty && isEmployeeIdEmpty && isJobTitleDefault &&
                isEmploymentStatusDefault && isSupervisorNameEmpty && isSubUnitDefault;
    }

}