Feature: Login functionality for OrangeHRM

  Background:
    Given I am on the login page

  @SmokeTest
  Scenario: Login with correct credentials
    When I enter valid username and password
    Then I should be logged in successfully

  @SmokeTest
  Scenario Outline: Login with invalid credentials
    When I enter invalid username "<username>" and invalid password "<password>"
    Then an error message should be displayed

    Examples:
      | username     | password     |
      | wrongUser    | wrongPass123 |
      | admin        | wrongPass    |
