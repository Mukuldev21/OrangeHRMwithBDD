package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//label[contains(text(),'Candidate Name')]/../following-sibling::div//input")
    private WebElement candidateNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@role='row']//div[@role='cell'][3]")
    private List<WebElement> candidateNamesInList;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isEmployeePresent(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        wait.until(ExpectedConditions.visibilityOf(candidateNameField)).clear();
        candidateNameField.sendKeys(fullName);
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(candidateNamesInList));
        return candidateNamesInList.stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase(fullName));
    }
}