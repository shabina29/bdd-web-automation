@Regression
Feature: SauceDemo Login

  @Smoke
  Scenario: Valid user should login successfully
    Given user is on SauceDemo login page
    When user enters valid username and password
    And clicks on login button
    Then Products page should be displayed

  @Negative
  Scenario: Invalid user should see the error message
    Given user is on SauceDemo login page
    When user enters invalid username and password
    And clicks on login button
    Then Error message should be displayed

  
  