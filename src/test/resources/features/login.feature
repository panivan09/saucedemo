Feature: Login

  @smoke
  Scenario: Successful login should open Product page
    Given I am on the login page
    When I login as a standard user
    Then Products page should be opened

  @regression
  Scenario Outline: Unsuccessful login should show error message
    Given I am on the login page
    When I am login with username "<username>" and password "<password>"
    Then I should see login error message "<message>"

    Examples:
      | username        | password       | message                                                                   |
      | standard_user   | wrong_password | Epic sadface: Username and password do not match any user in this service |
      | wrong_username  | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      | wrong_username  | wrong_password | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce   | Epic sadface: Username is required                                        |
      | standard_user   |                | Epic sadface: Password is required                                        |
      |                 |                | Epic sadface: Username is required                                        |
      | locked_out_user | secret_sauce   | Epic sadface: Sorry, this user has been locked out.                       |

  @regression @demo_fail
  Scenario: Should be failure and take screenshot
    Given I am on the login page
    When I am login with username "Should be failure" and password "Should be failure"
    And I click login button
    Then Products page should be opened
