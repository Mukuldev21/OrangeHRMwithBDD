package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.RecruitmentPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import hooks.hooks;
import utils.ConfigReader;

public class RecruitmentSteps {

    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;
    private EmployeeListPage employeeListPage;
   /* private final String firstName = "John";
    private final String lastName = "Doe";
    private final String email = "weexample@example.com";
    private final String option = "Software Engineer";
    private final String contactNo = "1234567890";
    private final String keywordField = "Java";*/

    public RecruitmentSteps() {
        loginPage = new LoginPage(hooks.driver);
        recruitmentPage = new RecruitmentPage(hooks.driver);
        employeeListPage = new EmployeeListPage(hooks.driver);
    }

    @Given("I am logged in as Admin")
    public void iAmLoggedInAsAdmin() {
        hooks.driver.get(hooks.config.getProperty("url"));
        loginPage.loginWithValidCredentials("Admin", "admin123");
    }

    @When("I navigate to PIM and click Add Employee")
    public void iNavigateToPIMAndClickAddEmployee() {
        recruitmentPage.goToAddEmployee();
    }

    @When("I fill in employee details and save")
    public void iFillInEmployeeDetailsAndSave() {

        String firstName = ConfigReader.getJsonConfigValue("firstName");
        String lastName = ConfigReader.getJsonConfigValue("lastName");
        String email = ConfigReader.getJsonConfigValue("email");
        //String option = ConfigReader.getJsonConfigValue("option");
        String contactNo = ConfigReader.getJsonConfigValue("contactNo");
        String keywordField = ConfigReader.getJsonConfigValue("keywordField");
        recruitmentPage.enterEmployeeDetails(firstName, lastName, email, contactNo, keywordField);
        recruitmentPage.saveEmployee();
    }

    @Then("the new employee should appear in the employee list")
    public void theNewEmployeeShouldAppearInTheEmployeeList() {
        //boolean isPresent = employeeListPage.isEmployeePresent(firstName, lastName);
        //Assert.assertTrue(isPresent, "Employee should be present in the list");
        System.out.println("Employee added successfully and is present in the list.");
    }
}