@wip
Feature: Registration
  Scenario: As a new user, I should be able to successfully register with email and password
    Given Accept type is applicationJson
    When User send POST request to the endpoint with email and password
    Then Status code should be 201
    And Response should include token


  Scenario: As a new user, I shouldn`t be able to register with just email
    Given Accept type is applicationJson
    When User send POST request to the endpoint with just email
    Then Status code should be 400
    And Response should include "Missing password" message


  Scenario:As a tester, I should be able to list all users
    Given Accept type is applicationJson
    When I send GET request to the endpoint
    Then Status code should be 200
    And Response should include following names
      | George  |
      | Janet   |
      | Emma    |
      | Eve     |
      | Charles |
      | Tracey  |