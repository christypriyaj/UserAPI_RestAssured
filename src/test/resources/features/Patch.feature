Feature: Update existing user with Patch request

Scenario Outline: Verify user is successfully able to update user information with patch and receive correct status code
    Given user is creating PATCH request from "<Scenario>" and "User.json"
    When user send patch request
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |patchValidName|

Scenario Outline: Verify user is successfully able to update user information with patch and validate field
    Given user is creating PATCH request from "<Scenario>" and "User.json"
    When user send patch request
    Then user should validate updated field
    
    Examples:
    |Scenario|
    |patchValidName|
        
Scenario Outline: Verify user gets error when updating user with Patch request with invalid data
    Given user is creating PATCH request from "<Scenario>" and "User.json"
    When user send patch request
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |patchDuplicateContact|
    |patchDuplicateEmail|