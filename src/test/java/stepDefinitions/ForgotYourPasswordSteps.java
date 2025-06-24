package stepDefinitions;

        import io.cucumber.java.en.And;
        import io.cucumber.java.en.Then;
        import io.cucumber.java.en.When;
        import hooks.hooks;
        import pages.LoginPage;

        public class ForgotYourPasswordSteps {

            private LoginPage loginPage;

            public ForgotYourPasswordSteps() {
                this.loginPage = new LoginPage(hooks.driver);
            }

            @When("I click on forgot password")
            public void clickOnForgotPassword() {
                loginPage.clickOnForgotYourPassword();
            }

            @Then("I am navigated to reset password page")
            public void verifyNavigationToResetPasswordPage() {
                loginPage.resetPasswordMessageIsDisplayed();
            }

            @And("enter username and click on reset password button")
            public void enterUsernameAndClickResetPasswordButton() {
                loginPage.enterUsernameOnResetPasswordPage();
                loginPage.clickOnResetPassword();
            }

            @Then("reset password link should be sent")
            public void verifyResetPasswordLinkSent() {
                loginPage.resetPassword();
            }
        }