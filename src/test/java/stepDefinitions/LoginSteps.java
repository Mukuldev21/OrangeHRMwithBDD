package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import hooks.hooks;
import hooks.StepTracker;
import hooks.StepErrorTracker;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        StepTracker.setLastStepText("Given I am on the login page");
        try {
            hooks.getDriver().get(hooks.config.getProperty("url"));
            loginPage = new LoginPage(hooks.getDriver());
            loginPage.userIsOnLoginPage();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() {
        StepTracker.setLastStepText("When I enter valid username and password");
        try {
            loginPage.loginWithValidCredentials("Admin", "admin123");
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        StepTracker.setLastStepText("Then I should be logged in successfully");
        try {
            loginPage.loggedInSuccessfully();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        StepTracker.setLastStepText("Then an error message should be displayed");
        try {
            loginPage.errorMessageIsDisplayed();
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }

    @When("I enter invalid username {string} and invalid password {string}")
    public void iEnterInvalidUsernameAndInvalidPassword(String username, String password) {
        StepTracker.setLastStepText("When I enter invalid username and invalid password");
        try {
            loginPage.loginWithInvalidCredentials(username, password);
            StepErrorTracker.clear();
        } catch (Exception e) {
            StepErrorTracker.setLastError(e.getMessage());
            throw e;
        }
    }
}