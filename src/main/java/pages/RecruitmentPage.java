package pages;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.Select;
        import utils.JavaScriptUtil;

public class RecruitmentPage extends BasePage {

            @FindBy(xpath = "//span[text()='Recruitment']")
            private WebElement recruitmentMenu;

            @FindBy(xpath = "//button[text()=' Add ']")
            private WebElement addButton;

            @FindBy(name = "firstName")
            private WebElement firstNameField;

            @FindBy(name = "middleName")
            private WebElement middleNameField;

            @FindBy(name = "lastName")
            private WebElement lastNameField;

            @FindBy(xpath = "//button[@type='submit']")
            private WebElement saveButton;

            @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
            private WebElement emailField;

            @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
            private WebElement contactNoField;

            @FindBy(xpath = "//select[@class='oxd-select-text-input']")
            private WebElement VacancyDropdown;

            @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[4]")
            private WebElement keywordField;

            public RecruitmentPage(WebDriver driver) {
                super(driver);
                PageFactory.initElements(driver, this);
            }

            public void goToAddEmployee() {
                recruitmentMenu.click();
                addButton.click();
            }
            public void scrollToEmailField() {
            JavaScriptUtil.scrollIntoView(driver, emailField);
            }

            public void enterEmail(String email) {
                scrollToEmailField();
                emailField.sendKeys(email);
            }

            public void enterContactNo(String contactNo) {
                JavaScriptUtil.scrollIntoView(driver, contactNoField);
                contactNoField.sendKeys(contactNo);
            }

            public void setVacancyDropdown(String option) {
               // try {
                    Select vacancy = new Select(VacancyDropdown);
                    vacancy.selectByValue(option);
                /*} catch (org.openqa.selenium.NoSuchElementException e) {
                    throw new IllegalArgumentException("Vacancy option '" + option + "' not found in dropdown.", e);
                }*/
            }

            public void enterKeyword(String keyword) {
                JavaScriptUtil.scrollIntoView(driver, keywordField);
                keywordField.sendKeys(keyword);
            }

            public void enterEmployeeDetails(String firstName, String lastName,
                                             String email,
                                             String contactNo, String keyword) {
                firstNameField.sendKeys(firstName);
                lastNameField.sendKeys(lastName);
                //setVacancyDropdown(option);
                enterEmail(email);
                enterContactNo(contactNo);
                enterKeyword(keyword);
            }


            public void saveEmployee() {
                saveButton.click();
            }
        }