package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.JavaScriptUtil;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddEmployeePage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[normalize-space()='Personal Details']")
    private WebElement personalDetailsHeader;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    @FindBy(xpath = "//span[text()='Employee Id already exists']")
    private WebElement duplicateIdErrorElement;

    @FindBy(xpath = "//label[normalize-space()='Male']")
    private WebElement genderInputMale;

    @FindBy(xpath = "//label[normalize-space()='Female']")
    private WebElement genderInputFemale;

    @FindBy(xpath = "//label[text()='Nationality']/following::div[contains(@class,'oxd-select-wrapper')][1]")
    private WebElement nationalityDropdown;

    @FindBy(xpath = "//label[text()='Marital Status']/following::div[contains(@class,'oxd-select-wrapper')][1]")
    private WebElement maritalStatusDropdown;

    @FindBy(xpath = "//label[text()='Date of Birth']/following::input[@class='oxd-input oxd-input--active'][1]")
    private WebElement dobField;

    @FindBy(xpath = "//label[text()='Other Id']/following::input[1]")
    private WebElement otherIdField;

    @FindBy(xpath = "//label[contains(text(),\"Driver's License Number\")]/parent::div/following-sibling::div//input")
    private WebElement licenseNumberField;

    @FindBy(xpath = "//label[text()='License Expiry Date']/following::input[@class='oxd-input oxd-input--active'][1]")
    private WebElement licenseExpiryField;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    private WebElement saveButtonInPersonalDetails;

    @FindBy(xpath = "//label[text()='Blood Type']/following::div[contains(@class,'oxd-select-wrapper')][1]")
    private WebElement bloodTypeDropdown;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[2]")
    private WebElement saveButtonInCustomFields;

    // Use a shorter default wait (5s) for most actions, longer only where needed
    private final WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private final WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        shortWait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        shortWait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
    }

    public void clickSave() {
        shortWait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
    public void verifyPersonalDetailsPage() {
        try {
            //waitForLoaderToDisappear();
            JavaScriptUtil.scrollIntoView(driver, personalDetailsHeader);
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            Assert.assertTrue(longWait.until(ExpectedConditions.visibilityOf(personalDetailsHeader)).isDisplayed(),
                    "Personal Details page is not displayed");
        } catch (Exception e) {
            System.out.println("DEBUG: Could not find Personal Details header. " + e.getMessage());
            throw e;
        }
    }

    public String getEmployeeId() {
        return wait.until(ExpectedConditions.visibilityOf(employeeIdField)).getAttribute("value");
    }

    public void enterEmployeeId(String id) {
        wait.until(ExpectedConditions.visibilityOf(employeeIdField)).clear();
        employeeIdField.sendKeys(id);
    }

    public void clearEmployeeIdField() {
        employeeIdField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(employeeIdField)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .perform();
        if (!employeeIdField.getAttribute("value").isEmpty()) {
            ((JavascriptExecutor) driver)
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
    // Utility method for custom dropdownsMore actions
    private void selectFromCustomDropdown(WebElement dropdown, String visibleText) {
        dropdown.click();
        // Wait for any option to be visible (adjust XPath if needed)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
        // Try to find the option by text (adjust XPath if options are in a different container)
        java.util.List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
        boolean found = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(visibleText.trim())) {
                option.click();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Dropdown option not found: " + visibleText);
        }
    }

    // Add this utility method in AddEmployeePage
    private void waitForLoaderToDisappear() {
        try {
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("div.oxd-form-loader")
            ));
        } catch (TimeoutException ignored) {}
    }
    // Wait for the personal details form to be ready
    public void waitForPersonalDetailsFormToBeReady() {
        // Wait for loader or overlays to disappear
        try {
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-form-loader")
            ));
            // Optionally, wait for the gender radio to be clickable
            shortWait.until(ExpectedConditions.elementToBeClickable(genderInputMale));
        } catch (TimeoutException ignored) {}
    }

    // Personal Details Section
    public void enterAllPersonalDetails(
            String gender, String nationality, String maritalStatus, String dob,
            String otherId, String licenseNumber, String licenseExpiry)
    {
        waitForPersonalDetailsFormToBeReady();
       /* WebElement genderElement = gender.equalsIgnoreCase("Male") ? genderInputMale : genderInputFemale;
        //waitForLoaderToDisappear();
        JavaScriptUtil.scrollIntoView(driver, genderElement);
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(genderElement)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderElement);
        }*/

        if (gender.equalsIgnoreCase("Male")) {
            if (!genderInputMale.isSelected()) {
                genderInputMale.click();
            }
        } else if (gender.equalsIgnoreCase("Female")) {
            if (!genderInputFemale.isSelected()) {
                genderInputFemale.click();
            }
        }

        selectFromCustomDropdown(nationalityDropdown, nationality);
        selectFromCustomDropdown(maritalStatusDropdown, maritalStatus);

        dobField.clear();
        dobField.sendKeys(dob);
        otherIdField.clear();
        otherIdField.sendKeys(otherId);
        licenseNumberField.clear();
        licenseNumberField.sendKeys(licenseNumber);
        licenseExpiryField.clear();
        licenseExpiryField.sendKeys(licenseExpiry);
        //selectFromCustomDropdown(bloodTypeDropdown, bloodType);
    }


    public void enterCustomFields(String bloodType) {
        waitForLoaderToDisappear();
        selectFromCustomDropdown(bloodTypeDropdown, bloodType);

    }

    public void clickOnSaveButtonInPersonalDetails() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButtonInPersonalDetails)).click();
        } catch (ElementClickInterceptedException e) {
            // Fallback: click with JavaScript if still intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButtonInPersonalDetails);
        }
    }

    public void clickOnSaveButtonInCustomFields() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButtonInCustomFields)).click();
        } catch (ElementClickInterceptedException e) {
            // Fallback: click with JavaScript if still intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButtonInCustomFields);
        }
    }

    // Java
    /*public void savePersonalDetails() {
        // Try to close any open dropdowns by clicking outside
        try {
            WebElement body = driver.findElement(By.tagName("body"));
            body.click();
        } catch (Exception ignored) {}

        // Wait for any overlaying <ul> to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ul[contains(@class,'oxd-select-dropdown') or @data-v-5327b38a]")));
        } catch (TimeoutException ignored) {}

        try {
            wait.until(ExpectedConditions.elementToBeClickable(savePersonalDetailsButton)).click();
        } catch (ElementClickInterceptedException e) {
            // Fallback: click with JavaScript if still intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", savePersonalDetailsButton);
        }
    }*/
    /*public String getGender() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(genderInputMale),
                ExpectedConditions.visibilityOf(genderInputFemale)
        ));
        JavaScriptUtil.scrollIntoView(driver, genderInputMale);
        JavaScriptUtil.scrollIntoView(driver, genderInputFemale);

        if (genderInputMale.isSelected()) return "Male";
        if (genderInputFemale.isSelected()) return "Female";
        return "";
    }*/
    // Java
    public String getGender() {
        try {
            // Try using the 'for' attribute
            String maleFor = genderInputMale.getAttribute("for");
            if (maleFor != null) {
                WebElement maleInput = driver.findElement(By.id(maleFor));
                if (maleInput.isSelected()) return "Male";
            }
            String femaleFor = genderInputFemale.getAttribute("for");
            if (femaleFor != null) {
                WebElement femaleInput = driver.findElement(By.id(femaleFor));
                if (femaleInput.isSelected()) return "Female";
            }
        } catch (Exception ignored) {}

        // Fallback: look for input inside the label
        try {
            WebElement maleInput = genderInputMale.findElement(By.xpath(".//input[@type='radio']"));
            if (maleInput.isSelected()) return "Male";
        } catch (Exception ignored) {}
        try {
            WebElement femaleInput = genderInputFemale.findElement(By.xpath(".//input[@type='radio']"));
            if (femaleInput.isSelected()) return "Female";
        } catch (Exception ignored) {}

        // Fallback: check all radio inputs in the section
        try {
            java.util.List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio']"));
            for (WebElement radio : radios) {
                if (radio.isSelected()) {
                    // Try to get the label text for the selected input
                    String id = radio.getAttribute("id");
                    if (id != null) {
                        WebElement label = driver.findElement(By.xpath("//label[@for='" + id + "']"));
                        return label.getText().trim();
                    }
                }
            }
        } catch (Exception ignored) {}

        return "";
    }

    public String getNationality() {
        // For custom dropdowns, you may need to get the selected value differently
        return nationalityDropdown.getText();
    }

    public String getMaritalStatus() {
        return maritalStatusDropdown.getText();
    }

    public String getDob() {
        return dobField.getAttribute("value");
    }

    public String getOtherId() {
        return otherIdField.getAttribute("value");
    }

    public String getLicenseNumber() {
        return licenseNumberField.getAttribute("value");
    }

    public String getLicenseExpiry() {
        String rawValue = licenseExpiryField.getAttribute("value");
        try {
            // Try parsing as yyyy-dd-MM and convert to yyyy-MM-dd
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(rawValue, inputFormat);
            return date.format(outputFormat);
        } catch (Exception e) {
            // If parsing fails, return as is
            return rawValue;
        }
    }

    public String getBloodType() {
        return bloodTypeDropdown.getText();
    }
}