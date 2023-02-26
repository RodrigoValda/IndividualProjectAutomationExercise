@LoginAutomation @UI
Feature: Login Automation Exercise

  @LoginSuccessful
  Scenario: A registered user should be able to login to the page
    Given I navigate to Automation Login page
    When I login to Automation Exercise page
    Then I should login successfully

  @WrongCredentials
  Scenario Outline: A user tries to login with wrong credentials
    Given I navigate to Automation Login page to logs in
    When I provide wrong credentials to login in the page with the following parameters
      | email                         | password    |
      | unregisteredemail@jalasoft.com| asdad123321 |
      | testing.email@jalasoft.com    | saddsa1223  |
    Then The error "<message>" should be displayed
    Examples:
      | message                              |
      | Your email or password is incorrect! |

  @SignupUser
  Scenario: A user is signup in Automation Exercise
    Given The user navigates to Automation Signup page
    When The user signup to automation exercise page
    Then The user should fill the signup form
    Then The user should signup successfully
      | password | firstName | lastName | address   | state  | city   | zipcode | mobileNumber|
      | asd123   | Rodrigo   | Arraya   | Address 1 | La Paz | La paz | 00000   | 201302202   |
    And The user should be redirected to the account created page

  @SignupWithoutInformation
  Scenario: A user is not signup in Automation Exercise without provide the required information
    Given The user navigates to Automation Signup page
    When The user signup to automation exercise page
    Then The user shouldn't fill the signup form
    And The user should signup successfully

  @Re-registerUser
  Scenario Outline: A user tries to re-register in automation exercise page
    Given The user navigates to Automation Signup page
    When The user tries to signup with an already exist user
      | name   | email |
      | rodrigo| testing.email@jalasoft.com|
    Then An error "<message>" should be displayed
    Examples:
      | message                      |
      | Email Address already exist! |


  @InvalidEmailSignup
  Scenario: A user tries to register in automation exercise page with invalid email
    Given The user navigates to Automation Signup page
    When The user tries to signup with invalid email
      | name   | email |
      | rodrigo| testing.emailInvalid|
    Then The user shouldn't be redirected to the home page

  @EmptyFieldsSignup
  Scenario: A user tries to register in automation exercise page with empty fields
    Given The user navigates to Automation Signup page
    When The user tries to signup with empty credentials
    Then The user shouldn't be redirected to the home page

  @EmptyFieldsLogin
  Scenario: A user tries to logs in to the Automation Exercise page with empty fields
    Given I navigate to Automation Login page to logs in
    When I provide empty values to the login
    Then The user shouldn't be redirected to the home page from the login

