@calculator
Feature: Test online calculator scenarios

  Background:
    Given I open chrome browser and start online calculator application

  @division
  Scenario Outline: User should be able to divide two numbers
    Given I want check the scenario <context>
    When I do the math <value1> <operator> by <value2>
    Then I should see the result is <result>
    Examples:
      | context                                                                                              | value1 | operator | value2 | result       |
      | divide two positive integers                                                                         | 1500   | divide   | 2000   | 0.75         |
      | divide zero by a integer divisor                                                                     | 0      | divide   | 2000   | 0            |
      | divide a negative dividend by a positive divisor                                                     | -1500  | divide   | 2000   | -0.75        |
      | divide a negative floating point dividend by a positive divisor                                      | -3.123 | divide   | 5      | -0.6246      |
      | divide a negative integer dividend by a positive floating point divisor to nine significiant figures | -5     | divide   | 3.123  | -1.60102466  |
      | divide an floating point dividend by an integer divisor                                              | 4.21   | divide   | 3      | 1.40333333   |
      | divide an integer dividend by a floating point divisor                                               | 10     | divide   | 3.123  | 3.20204931   |
      | divide two floating point numbers                                                                    | 0.234  | divide   | 3.123  | 0.07492795   |

  @division
  Scenario: User should see error for division by zero
    When I do the math 1500 divide by 0
    Then I should see the error message

  @division
  Scenario Outline: User should be able to divide with large numbers (9 digits)
    Given I want check the scenario <context>
    When I do the math <value1> <operator> by <value2>
    Then I should see the result is <result>
    Examples:
      | context                                     | value1     | operator | value2     | result      |
      | divide two large integers                   | -987654321 | divide   | 123456789  | -8.00000007 |
      | divide an many digit floating point numbers | 1.23456789 | divide   | 2.10987654 | 0.5851375   |

  @division
  Scenario Outline: User should be able to divide a number to the results of a previous operation
    Given I want check the scenario <context>
    When I do the chain math <value1> <operator1> by <value2> and <operator2> by <value3>
    Then I should see the result is <result>
    Examples:
      | context                                                                         | value1 | operator1 | value2 | operator2 | value3     | result      |
      | divide the result of a previous operation by a positive floating point number   | 1500   | subtract  | 2000   | divide    | 3.12       | -160.25641  |
      | divide the result of a previous operation by a positive integer                 | 1500   | subtract  | 2000   | divide    | 312        | -1.6025641  |
      | divide the result of a previous operation by a many digit floating point number | 2000   | subtract  | 1500   | divide    | 1234.56789 | 0.405       |
      | divide the result of a previous operation by a large integer                    | 1500   | subtract  | 2000   | divide    | 123456789  | -0.00000405 |