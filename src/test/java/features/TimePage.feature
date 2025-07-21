Feature: Time Page Functionality

  Background:
    Given I am on the login page
    When I enter valid username and password
    Then I should be logged in successfully
    And I navigate to the Time page

  @TimePage
  Scenario: Search for an employee timesheet
    When I enter employee name from json in the timesheet search field
    And I click the view button
    Then the timesheet for employee from json should be displayed

  @TimePage
  Scenario: View my timesheet
    When I click the "My Timesheet" button
    Then my timesheet should be displayed