Feature: Checkout

  Background:
    Given I am logged in as a standard user

  @smoke
  Scenario: Complete purchase with one item
    When I add backpack to cart
    And I open cart
    And I proceed to checkout
    And I fill checkout information with valid user data
    And I finish checkout
    Then I should see checkout complete message "Thank you for your order!"

  @smoke
  Scenario: Logout should return user to Login page
    When I logout from Products page
    Then Login page should be opened