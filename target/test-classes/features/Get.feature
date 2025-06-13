Feature: Get User details

Scenario: Validate  response status code to GET all users
    Given user is creating GET request
    When user performs GET operation with Endpoint  /users
    Then user should receive 200 ok valid status code

Scenario: Validate correct status line is displayed for GET all users
    Given user is creating GET request
    When user performs GET operation with Endpoint  /users
    Then user should receive correct status line "HTTP/1.1 200 OK"

Scenario: Validate response body is in the format Content- Type JSON for GET all users
    Given user is creating GET request
    When user performs GET operation with Endpoint  /users
    Then user should receive response body in the format  Content- Type JSON

Scenario Outline: Validate GET request for user with valid user id
    Given user is creating GET request from "<Scenario>" and "User.json"
    When user performs GET operation with Endpoint user/userID
    Then user should receive correct status code 
    
    Examples:
    |Scenario|
    |getUserIDValid|
    
Scenario Outline: Validate status code for GET user with invalid user id
    Given user is creating GET request from "<Scenario>" and "User.json"
    When user performs GET operation with Endpoint user/userID
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |getUserIDInvalid|
    
Scenario Outline: Validate status line displays error message for GET user with invalid user id
    Given user is creating GET request from "<Scenario>" and "User.json"
    When user performs GET operation with Endpoint user/userID
    Then Status line should contain error message
    
    Examples:
    |Scenario|
    |getUserIDInvalid|     
    
Scenario Outline: Validate GET request for user with valid user first name
    Given user is creating GET request from "<Scenario>" and "User.json"
    When user performs GET operation with Endpoint users/username/userFirstName
    Then user should receive correct status code 
    
    Examples:
    |Scenario|
    |getUserFirstNameValid|
    
Scenario Outline: Validate status code for GET user with invalid user first name
    Given user is creating GET request from "<Scenario>" and "User.json"
    When user performs GET operation with Endpoint users/username/userFirstName
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |getUserFirstnameInvalid|
    
Scenario Outline: Validate status line displays error message for GET user with invalid user first name
    Given user is creating GET request from "<Scenario>" and "User.json"
    When user performs GET operation with Endpoint users/username/userFirstName
    Then Status line should contain error message
    
    Examples:
    |Scenario|
    |getUserFirstnameInvalid|         