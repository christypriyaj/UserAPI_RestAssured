Feature: Create a new user

Scenario Outline: Verify that new user created successfully with valid status code
    Given user sets a post request with valid endpoint from "<Scenario>" and "User.json"
    When user send post request with valid first name,last name,contact number,email id,user address
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |validUser1|
    
Scenario Outline: Verify that new user created successfully with valid content type
    Given user sets a post request with valid endpoint from "<Scenario>" and "User.json"
    When user send post request with valid first name,last name,contact number,email id,user address
    Then user should receive correct content type
    
    Examples:
    |Scenario|
    |validUser1|
    
Scenario Outline: Verify that new user created successfully with valid status line
    Given user sets a post request with valid endpoint from "<Scenario>" and "User.json"
    When user send post request with valid first name,last name,contact number,email id,user address
    Then user should receive correct status line
    
    Examples:
    |Scenario|
    |validUser1|
    
Scenario Outline: Verify that new user created successfully with valid schema
    Given user sets a post request with valid endpoint from "<Scenario>" and "User.json"
    When user send post request with valid first name,last name,contact number,email id,user address
    Then user should receive correct schema
    
    Examples:
    |Scenario|
    |validUser1|
    
Scenario Outline: Verify user gets error with correct status code when creating user with invalid data
    Given user sets a post request with valid endpoint from "<Scenario>" and "User.json"
    When user send a post request with invalid data
    Then user should get Error with correct status code
    
    Examples:
    |Scenario|
    |nonAlphaFirstName|
    |nonAlphaLastName|
    |duplicateContact|
    |invalidLenContact|
    |invalidEmail|
    |duplicateEmail|
    
Scenario Outline: Verify user gets error with correct error message when creating user with invalid data
    Given user sets a post request with valid endpoint from "<Scenario>" and "User.json"
    When user send a post request with invalid data
    Then user should get Error with correct error message
    
    Examples:
    |Scenario|
    |nonAlphaFirstName|
    |nonAlphaLastName|
    |duplicateContact|
    |invalidLenContact|
    |invalidEmail|
    |duplicateEmail|
    
  Scenario Outline: Verify user reveives 401 Unauthorized when sending request without authorization
    Given User has No Auth and creates request from "<Scenario>" and "User.json"
    When user send post request with valid first name,last name,contact number,email id,user address
    Then user should receive correct status code
    
    Examples:
    |Scenario|
    |NoAuth|