Feature: Customer wants to verify an offer to sell his car

  Scenario Outline: To fetch all car brands available in Europe location "<locale>"
    Given the base url "http://api-aws-eu-qa-1.auto1-test.com" to sell my car
    Then user should able to view available Car brands in the market with parameter "<manufacturer>" and "<locale>"
    Then user should able to view Car types for each Brands with parameter "<carType>" and "<locale>"
    And user should able to view Built Year for each Car Brands with parameter "<builtYear>" and "<locale>"
    And user confirms if all Car brands are displayed
    And user checks total car brands displayed as per location <carBrands>
    And user checks if page, pageSize, totalPageCount and wkda values are not empty
    Then user checks if Car types available for all Car Brands
    And user checks if page, pageSize, totalPageCount in Car types for all Brands
    And user checks if car Built years page, pageSize, totalPageCount displayed for all Brands
    And user checks if car built years displayed for each model in all Brands

    Examples:
      | manufacturer               | carType                  | builtYear                 | locale | carBrands |
      | /v1/car-types/manufacturer | /v1/car-types/main-types | /v1/car-types/built-dates | it_IT  | 73        |
      | /v1/car-types/manufacturer | /v1/car-types/main-types | /v1/car-types/built-dates | fr_FR  | 60        |
      | /v1/car-types/manufacturer | /v1/car-types/main-types | /v1/car-types/built-dates | es_ES  | 63        |
      | /v1/car-types/manufacturer | /v1/car-types/main-types | /v1/car-types/built-dates | de_DE  | 79        |
