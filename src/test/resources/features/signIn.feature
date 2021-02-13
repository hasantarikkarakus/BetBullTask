@wip
Feature: Sign in

  Background:
    Given User is on the sign in page

  Scenario:As a user, I shouldn`t be able to login without email and password
    When User signs in without email and password
    Then User should see the "An email address required." message

  Scenario:As a user, I shouldn`t be able to login with just email
    When User signs in with just email
    Then User should see the "Password is required." message

  Scenario:As a user, I shouldn`t be able to login with just password
    When User signs in with just password
    Then User should see the "An email address required." message

  Scenario:As a user, I shouldn`t be able to login with incorrect credentials
    When User signs in with incorrect credentials
    Then User should see the "Authentication failed." message