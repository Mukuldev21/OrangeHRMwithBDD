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

    @When("I enter all personal details from json")
    public void enterAllPersonalDetailsFromJson() {
        StepTracker.setLastStepText("When I enter all personal details from json");
        try {
            String gender = ConfigReader.getJsonConfigValue("gender");
            String nationality = ConfigReader.getJsonConfigValue("nationality");
            String maritalStatus = ConfigReader.getJsonConfigValue("maritalStatus");
            String dob = ConfigReader.getJsonConfigValue("dob");
            String otherId = ConfigReader.getJsonConfigValue("otherId");
            String licenseNumber = ConfigReader.getJsonConfigValue("licenseNumber");
            String licenseExpiry = ConfigReader.getJsonConfigValue("licenseExpiry");
            String bloodType = ConfigReader.getJsonConfigValue("bloodType");
            addEmployeePage.enterAllPersonalDetails(
                    gender, nationality, maritalStatus, dob, otherId,
                    licenseNumber, licenseExpiry,bloodType
            );
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
            addEmployeePage.savePersonalDetails();
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
            Assert.assertEquals(addEmployeePage.getGender(), ConfigReader.getJsonConfigValue("gender"));
            Assert.assertEquals(addEmployeePage.getNationality(), ConfigReader.getJsonConfigValue("nationality"));
            Assert.assertEquals(addEmployeePage.getMaritalStatus(), ConfigReader.getJsonConfigValue("maritalStatus"));
            Assert.assertEquals(addEmployeePage.getDob(), ConfigReader.getJsonConfigValue("dob"));
            Assert.assertEquals(addEmployeePage.getOtherId(), ConfigReader.getJsonConfigValue("otherId"));
            Assert.assertEquals(addEmployeePage.getLicenseNumber(), ConfigReader.getJsonConfigValue("licenseNumber"));
            Assert.assertEquals(addEmployeePage.getLicenseExpiry(), ConfigReader.getJsonConfigValue("licenseExpiry"));
            Assert.assertEquals(addEmployeePage.getBloodType(), ConfigReader.getJsonConfigValue("bloodType"));
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }
}