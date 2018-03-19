#Each feature file contains one feature
#  Feature files use Gherkin language - business language
Feature: Test the login functionality on sdet university

#  A feature may have given different specific scenarios

  Scenario: the user should be able to login with correct username and correct password
#  Preconditions
    Given user is on the login page
#    Actions
    When  user enters correct username and correct password
#    Assertions
    Then user gets confirmation

  Scenario Outline: The user should be able to login
    Given user is on the login page
    When  user enters email <username>
    And user enters password <password>
    And user clicks login button
    Then user gets confirmation

    Examples:
    | username | password |
    | tim@testemail.com | trpass |
    | tim@testemail.com | rwpass |
    | rw@testemail.com | trpass |
    | rw@testemail.com | rwpass |