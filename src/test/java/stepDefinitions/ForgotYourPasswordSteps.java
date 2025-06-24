package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import hooks.hooks;
import pages.LoginPage;

public class ForgotYourPasswordSteps {

    private LoginPage loginPage;

    @When("I click on forgot password")
    public void i_click_on_forgot_password() {
        // Write code here that turns the phrase above into concrete actions
        loginPage = new LoginPage(hooks.driver);
        loginPage.clickOnForgotYourPassword();
    }

    @Then("I am navigated to reset password page")
    public void i_am_navigated_to_reset_password_page() {
        // Write code here that turns the phrase above into concrete actions
        loginPage = new LoginPage(hooks.driver);
        loginPage.resetPasswordMessageIsDisplayed();
    }

    @And("enter username and click on reset password button")
    public void enter_username_and_click_on_reset_password_button() {
        // Write code here that turns the phrase above into concrete actions
        loginPage = new LoginPage(hooks.driver);
        loginPage.enterUsernameOnResetPasswordPage();
        loginPage.clickOnResetPassword();
    }

    @Then("reset password link should be sent")
    public void reset_password_link_should_be_sent() {
        // Write code here that turns the phrase above into concrete actions

        loginPage = new LoginPage(hooks.driver);
        loginPage.resetPassword();
    }



}
