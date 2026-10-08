Feature: User Login

  Background:
    Given the user is on the login page

  Scenario: Successful login with valid credentials
    When the user enters valid username and password
    And the user clicks the login button
    Then the Products page should be displayed

  Scenario: Login with invalid username and valid password
    When the user enters username "invalid_user" and password "secret_sauce"
    And the user clicks the login button
    Then the login error message "Epic sadface: Username and password do not match any user in this service" should be displayed

  Scenario: Login with valid username and invalid password
    When the user enters username "standard_user" and password "invalid_password"
    And the user clicks the login button
    Then the login error message "Epic sadface: Username and password do not match any user in this service" should be displayed

  Scenario: Login with empty username
    When the user enters username "" and password "secret_sauce"
    And the user clicks the login button
    Then the login error message "Epic sadface: Username is required" should be displayed

  Scenario: Login with empty password
    When the user enters username "standard_user" and password ""
    And the user clicks the login button
    Then the login error message "Epic sadface: Password is required" should be displayed