Feature: Customer wants to verify an offer to sell his car

  Background: To fetch all car brands available in Europe
    Given the base url "http://api-aws-eu-qa-1.auto1-test.com/v1/car-types" to sell my car
    Then user should able to view available Car brands in the market with parameter "/manufacturer"
    And user should able to view Car types for each Brands
    #And user should able to view Built Year for each Car Brands

  Scenario: Verify all available Car Brands displayed
    And user confirms if all Car brands are displayed

  Scenario: Verify page, pageSize, totalPageCount for available Car Brands
    And user checks if page, pageSize, totalPageCount and wkda values are not empty

  Scenario: Verify Car main types available for all Car Brands
    Then user checks if Car types available for all Car Brands

  Scenario: Verify page, pageSize and totalPageCount displayed for Car main-types for all Car Brands
    And user checks if page, pageSize, totalPageCount in Car types for all Brands

  Scenario: Verify car Built years page, pageSize and totalPageCount displayed all Brands
    And user checks if car Built years page, pageSize, totalPageCount displayed for all Brands




