package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import hooks.hooks;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        hooks.driver.get(hooks.config.getProperty("url"));
        loginPage = new LoginPage(hooks.driver);
        loginPage.userIsOnLoginPage();
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() {
        loginPage.loginWithValidCredentials("Admin", "admin123");
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        loginPage.loggedInSuccessfully();
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        loginPage.errorMessageIsDisplayed();
    }

    @When("I enter invalid username {string} and invalid password {string}")
    public void iEnterInvalidUsernameAndInvalidPassword(String username, String password) {
        loginPage.loginWithInvalidCredentials(username, password);
    }
}