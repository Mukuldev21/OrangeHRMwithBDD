package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AddEmployeePage;
import hooks.hooks;
import pages.DashboardPage;
import pages.PIMPage;
import utils.ConfigReader;
import hooks.StepTracker;
import hooks.StepErrorTracker;

public class AddEmployeeSteps {

    private AddEmployeePage addEmployeePage;
    private String employeeId;

    @When("I navigate to the Add Employee page")
    public void iNavigateToAddEmployeePage() {
        StepTracker.setLastStepText("When I navigate to the Add Employee page");
        try {
            DashboardPage dashboardPage = new DashboardPage(hooks.driver);
            dashboardPage.clickPIMMenu();
            PIMPage pimPage = new PIMPage(hooks.driver);
            pimPage.clickAddEmployee();
            addEmployeePage = new AddEmployeePage(hooks.driver);
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I enter employee first name and last name from json")
    public void iEnterEmployeeDetailsFromJson() {
        StepTracker.setLastStepText("When I enter employee first name and last name from json");
        try {
            String firstName = ConfigReader.getJsonConfigValue("firstName");
            String lastName = ConfigReader.getJsonConfigValue("lastName");
            addEmployeePage.enterFirstName(firstName);
            addEmployeePage.enterLastName(lastName);
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I save the new employee")
    public void iSaveTheNewEmployee() {
        StepTracker.setLastStepText("When I save the new employee");
        try {
            addEmployeePage.clickSave();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @Then("the Personal Details page should be displayed")
    public void personalDetailsPageShouldBeDisplayed() {
        StepTracker.setLastStepText("Then the Personal Details page should be displayed");
        try {
            addEmployeePage.verifyPersonalDetailsPage();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I note the generated employee id")
    public void iNoteTheGeneratedEmployeeId() {
        StepTracker.setLastStepText("When I note the generated employee id");
        try {
            employeeId = addEmployeePage.getEmployeeId();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I enter the noted employee id")
    public void iEnterTheNotedEmployeeId() {
        StepTracker.setLastStepText("When I enter the noted employee id");
        try {
            addEmployeePage.clearEmployeeIdField(); // Add this method in AddEmployeePage
            addEmployeePage.enterEmployeeId(employeeId);
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @Then("a duplicate employee id error should be displayed")
    public void duplicateEmployeeIdErrorShouldBeDisplayed() {
        StepTracker.setLastStepText("Then a duplicate employee id error should be displayed");
        try {
            Assert.assertTrue(addEmployeePage.isDuplicateEmployeeIdErrorDisplayed(),
                    "Duplicate employee ID error was not displayed");
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }
}