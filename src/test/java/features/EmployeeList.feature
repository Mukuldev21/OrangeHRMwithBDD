Feature: Employee List Search

  Background:
    Given I am on the login page
    When I enter valid username and password
    Then I should be logged in successfully
    And I navigate to the Employee List page


  @Search
  Scenario: Search employee by name
    When I enter employee name from json in the employee name search field
    And I click the search button
    Then the search results should display employee with name from json

  @Search
  Scenario: Search employee by ID
    When I enter employee ID from json in the employee ID search field
    And I click the search button
    Then the search results should display employee with ID from json

  @Search
  Scenario: Search employee by job title
    When I select job title from json from the job title dropdown
    And I click the search button
    Then the search results should display employees with job title from json

  @Search
    Scenario: Search for a non-existent employee
      When I enter employee name from non-existent json in the employee name search field
      And I click the search button
      Then I should see an non-existent error message

  @Search
  Scenario: Search employee by invalid ID
    When I enter an invalid employee ID in the employee ID search field
    And I click the search button
    Then I should see an error message

  @Search
  Scenario: Search employee by employment status
    When I select an employment status from the employment status dropdown
    And I click the search button
    Then the search results should display employees with the selected employment status

  @Search
  Scenario: Search employee by supervisor name
    When I enter a supervisor name in the supervisor name search field
    And I click the search button
    Then the search results should display employees reporting to the supervisor

  @Search
  Scenario: Search employee by job title and sub unit
    When I select a job title from the job title dropdown
    And I select a sub unit from the sub unit dropdown
    And I click the search button
    Then the search results should display employees matching the job title and sub unit

  @Search
  Scenario: Reset search filters
    When I enter all the details from the json in the search field
    And I click the reset button
    Then all search fields should be cleared