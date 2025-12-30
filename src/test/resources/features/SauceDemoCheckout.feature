@Regression
Feature: SauceDemo Checkout Flow

  @Ignore
  Scenario: User should be able to complete checkout successfully
    Given user is on SauceDemo login page
    When user enters valid username and password
    And clicks on login button
    And user adds a product to the cart
    And user navigates to cart
    And user proceeds to checkout
    And user enters checkout details
    And user completes the checkout
    Then order confirmation message should be displayed


