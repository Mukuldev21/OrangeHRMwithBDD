Feature: Add Employee in PIM

          Background:
            Given I am on the login page
            When I enter valid username and password
            Then I should be logged in successfully

          @PIM
          Scenario: Add a new employee with valid details from json
            When I navigate to the Add Employee page
            And I enter employee first name and last name from json
            And I save the new employee
            Then the Personal Details page should be displayed


          @PIM2
            Scenario: Prevent adding duplicate employee with same employee ID
              When I navigate to the Add Employee page
              And I enter employee first name and last name from json
              And I note the generated employee id
              And I save the new employee
              Then the Personal Details page should be displayed

              When I navigate to the Add Employee page
              And I enter employee first name and last name from json
              And I enter the noted employee id
              And I save the new employee
              Then a duplicate employee id error should be displayed

          @PIM3
          Scenario: Enter and save all personal details for a new employee from json
            When I navigate to the Add Employee page
            And I enter employee first name and last name from json
            And I save the new employee
            Then the Personal Details page should be displayed
            When I enter all personal details from json
            And I save the personal details
            Then the personal details should match the json data