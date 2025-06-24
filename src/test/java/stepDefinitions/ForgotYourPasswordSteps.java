package stepDefinitions;

        import io.cucumber.java.en.And;
        import io.cucumber.java.en.Then;
        import io.cucumber.java.en.When;
        import hooks.hooks;
        import pages.LoginPage;
        import hooks.StepTracker;

        public class ForgotYourPasswordSteps {

            private LoginPage loginPage;

            public ForgotYourPasswordSteps() {
                this.loginPage = new LoginPage(hooks.driver);
            }

            @When("I click on forgot password")
            public void clickOnForgotPassword() {
                StepTracker.setLastStepText("When I click on forgot password");
                loginPage.clickOnForgotYourPassword();
            }

            @Then("I am navigated to reset password page")
            public void verifyNavigationToResetPasswordPage() {
                StepTracker.setLastStepText("Then I am navigated to reset password page");
                loginPage.resetPasswordMessageIsDisplayed();
            }

            @And("enter username and click on reset password button")
            public void enterUsernameAndClickResetPasswordButton() {
                StepTracker.setLastStepText("And enter username and click on reset password button");
                loginPage.enterUsernameOnResetPasswordPage();
                loginPage.clickOnResetPassword();
            }

            @Then("reset password link should be sent")
            public void verifyResetPasswordLinkSent() {
                StepTracker.setLastStepText("Then reset password link should be sent");
                loginPage.resetPassword();
            }
        }