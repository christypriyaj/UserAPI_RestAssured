Feature: Update existing user with Put request

Scenario Outline: Verify user is successfully able to update user information and receive correct status code
    Given user is creating PUT request from "<Scenario>" and "User.json"
    When user send put request
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |putValidLastName|
    
Scenario Outline: Verify user is successfully able to update user information and validate field
    Given user is creating PUT request from "<Scenario>" and "User.json"
    When user send put request
    Then user should validate updated field
    
    Examples:
    |Scenario|
    |putValidLastName|
    
Scenario Outline: Verify user gets error when updating user with Put request with invalid data
    Given user is creating PUT request from "<Scenario>" and "User.json"
    When user send put request
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |putDuplicateContact|
    |putDuplicateEmail|