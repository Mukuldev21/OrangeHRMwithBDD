Feature: Forgot Password functionality

  @ForgotPassword
  Scenario: User clicks on Forgot Your Password
    Given  I am on the login page
    When I click on forgot password
    Then I am navigated to reset password page
    And  enter username and click on reset password button
    Then reset password link should be sent

