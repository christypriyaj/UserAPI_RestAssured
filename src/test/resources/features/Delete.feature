Feature: Delete existing user

Scenario Outline: Validate Delete user with valid user name should have correct status code
    Given user is creating DELETE request from "<Scenario>" and "User.json"
    When user send delete request with user Name
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |deleteValidUser|
    
Scenario Outline: Validate Delete user with valid user name should have correct response message
    Given user is creating DELETE request from "<Scenario>" and "User.json"
    When user send delete request with user Name
    Then user should receive correct response message
    
    Examples:
    |Scenario|
    |deleteValidUser|
    
Scenario Outline: Validate Delete user with nonexistent user name should have correct status code
    Given user is creating DELETE request with invalid user from "<Scenario>" and "User.json"
    When user send delete request with user Name
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |deleteInvalidUserName|
    
Scenario Outline: Validate Delete user with nonexistent user name should have correct response message
    Given user is creating DELETE request with invalid user from "<Scenario>" and "User.json"
    When user send delete request with user Name
    Then user should receive correct error response message
    
    Examples:
    |Scenario|
    |deleteInvalidUserName|
    
Scenario Outline: Validate Delete user with valid user id should have correct status code
    Given user is creating DELETE request from "<Scenario>" and "User.json"
    When user send delete request with valid user id
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |deleteValidUser|
    
Scenario Outline: Validate Delete user with valid user id should have correct response message
    Given user is creating DELETE request from "<Scenario>" and "User.json"
    When user send delete request with valid user id
    Then user should receive correct response message
    
    Examples:
    |Scenario|
    |deleteValidUser|
    
Scenario Outline: Validate Delete user with nonexistent user id should have correct status code
    Given user is creating DELETE request with invalid user from "<Scenario>" and "User.json"
    When user send delete request with invalid user id
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |deleteInvalidUserId|
    
Scenario Outline: Validate Delete user with nonexistent user id should have correct response message
    Given user is creating DELETE request with invalid user from "<Scenario>" and "User.json"
    When user send delete request with invalid user id
    Then user should receive correct error response message
    
    Examples:
    |Scenario|
    |deleteInvalidUserId|