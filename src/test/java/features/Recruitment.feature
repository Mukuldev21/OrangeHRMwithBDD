Feature: Add New Employee

  @AddNewEmployee
  Scenario: Admin adds a new employee
    Given I am logged in as Admin
    When I navigate to PIM and click Add Employee
    And I fill in employee details and save
    Then the new employee should appear in the employee list