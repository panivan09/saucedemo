Feature: Products sorting

  Background:
    Given I am logged in as standard user

  @regression
  Scenario: Sort products by name A to Z
    When I sort products by "az"
    Then products should be sorted A to Z
