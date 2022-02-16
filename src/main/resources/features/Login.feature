Feature: Customer wants to verify an offer to sell his car

  Background: To fetch all car brands available in Europe
    Given the base url "http://api-aws-eu-qa-1.auto1-test.com/v1/car-types" to sell my car
    Then user should able to view available Car brands in the market with parameter "/manufacturer"

  Scenario: Verify the drop down options in manufacturer, model and first registration
    And user checks if all Car brands are displayed

  Scenario Outline: Verify the drop down options in manufacturer, model and first registration
    And I want to see available model of selected Car with parameter "<carType>"
    Then I want to select make of my Car with parameter "<builtYear>"

    Examples:
      | carType     | builtYear    |
      | /main-types | /built-dates |