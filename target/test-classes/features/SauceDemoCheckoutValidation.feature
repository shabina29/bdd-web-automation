@Regression
Feature: SauceDemo Checkout Validation

  @Ignore
  Scenario: User should see error when checkout details are missing
    Given user is on SauceDemo login page
    When user enters valid username and password
    And clicks on login button
    And user adds a product to the cart
    And user navigates to cart
    And user proceeds to checkout
    And user continues checkout without entering details
    Then error message should be displayed on checkout page
