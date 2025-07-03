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
            DashboardPage dashboardPage = new DashboardPage(hooks.getDriver());
            dashboardPage.clickPIMMenu();
            PIMPage pimPage = new PIMPage(hooks.getDriver());
            pimPage.clickAddEmployee();
            addEmployeePage = new AddEmployeePage(hooks.getDriver());
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
            String firstName = hooks.employeeDetailsJson.get("firstName").getAsString();
            String lastName = hooks.employeeDetailsJson.get("lastName").getAsString();
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

    @When("I enter all personal details from json")
    public void enterAllPersonalDetailsFromJson() {
        StepTracker.setLastStepText("When I enter all personal details from json");
        try {
            String gender = hooks.employeeDetailsJson.get("gender").getAsString();
            String nationality = hooks.employeeDetailsJson.get("nationality").getAsString();
            String maritalStatus = hooks.employeeDetailsJson.get("maritalStatus").getAsString();
            String dob = hooks.employeeDetailsJson.get("dob").getAsString();
            String otherId = hooks.employeeDetailsJson.get("otherId").getAsString();
            String licenseNumber = hooks.employeeDetailsJson.get("licenseNumber").getAsString();
            String licenseExpiry = hooks.employeeDetailsJson.get("licenseExpiry").getAsString();
            String bloodType = hooks.employeeDetailsJson.get("bloodType").getAsString();
            addEmployeePage.enterAllPersonalDetails(
                    gender, nationality, maritalStatus, dob, otherId,
                    licenseNumber, licenseExpiry
            );
            addEmployeePage.enterCustomFields(bloodType);
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I save the personal details")
    public void savePersonalDetails() {
        StepTracker.setLastStepText("When I save the personal details");
        try {
            addEmployeePage.clickOnSaveButtonInPersonalDetails();
            addEmployeePage.clickOnSaveButtonInCustomFields();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }


    @Then("the personal details should match the json data")
    public void verifyPersonalDetails() {
        StepTracker.setLastStepText("Then the personal details should match the json data");
        try {
            Assert.assertEquals(addEmployeePage.getGender(), hooks.employeeDetailsJson.get("gender").getAsString());
            Assert.assertEquals(
                    addEmployeePage.getNationality().toLowerCase(),
                    hooks.employeeDetailsJson.get("nationality").getAsString().toLowerCase()
            );
            Assert.assertEquals(addEmployeePage.getMaritalStatus(), hooks.employeeDetailsJson.get("maritalStatus").getAsString());
            Assert.assertEquals(addEmployeePage.getDob(), hooks.employeeDetailsJson.get("dob").getAsString());
            Assert.assertEquals(addEmployeePage.getOtherId(), hooks.employeeDetailsJson.get("otherId").getAsString());
            Assert.assertEquals(addEmployeePage.getLicenseNumber(), hooks.employeeDetailsJson.get("licenseNumber").getAsString());
            Assert.assertEquals(addEmployeePage.getLicenseExpiry(), hooks.employeeDetailsJson.get("licenseExpiry").getAsString());
            Assert.assertEquals(addEmployeePage.getBloodType(), hooks.employeeDetailsJson.get("bloodType").getAsString());
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }
}