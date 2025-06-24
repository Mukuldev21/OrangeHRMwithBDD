package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import hooks.hooks;

import java.time.Duration;

public class LoginSteps{


    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        hooks.driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(hooks.driver);
        loginPage.userIsOnLoginPage();
        System.out.println("User is On Login Page: Test passed");

    }
    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.loginWithValidCredentials("Admin", "admin123");
        System.out.println("Entered the username and password successfully: Test passed");
    }
    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.loggedInSuccessfully();
        System.out.println("User is successfully logged in: Test passed");
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        loginPage.errorMessageIsDisplayed();
        System.out.println("Error Message is Displayed");
    }

    @When("I enter invalid username {string} and invalid password {string}")
    public void iEnterInvalidUsernameAndInvalidPassword(String username, String password) {

        loginPage.loginWithInvalidCredentials(username, password);
        System.out.println("Entered the invalid username and invalid password");
    }
}
