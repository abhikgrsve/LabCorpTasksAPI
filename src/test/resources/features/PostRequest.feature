#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Verify POST request to create an order

  Scenario: Send order details and verify customer, payment, and product information
    Given I send a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor" with the order details
    Then the status code should be 200
    And the order should contain correct customer information
    And the order should contain correct payment details
    And the order should contain correct product details
