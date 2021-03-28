@calculator
Feature: Test online calculator scenarios
  Background:
    Given I open chrome browser and start online calculator application

  @addition
  Scenario Outline: User should be able to add two numbers
    Given I want check the scenario <context>
    When I do the math <value1> <operator> by <value2>
    Then I should see the result is <result>
    Examples:
      | context                                                    | value1   | operator | value2 | result  |
      | add two positive integers                                  | 1500     | add      | 2000   | 3500    |
      | add a negative integer to a positive floating point number | -1       | add      | 1.0000 | 0       |
      | add a floating point number to an integer                  | 10.1     | add      | 2      | 12.1    |
      | add an integer to a floating point number                  | 10       | add      | 9.9999 | 19.9999 |
      | add two floating point numbers                             | 34.999   | add      | 1.0    | 35.999  |
      | add a negative integer and zero                            | -5       | add      | 0      | -5      |
      | add zero and a positive integer                            | 0        | add      | 5      | 5       |
      | add a negative integer with a positive number              | -5       | add      | 5      | 0       |
      | add a add a negative floating point and a positive integer | -1987.50 | add      | 1987   | -0.5    |

  @addition
  Scenario Outline: User should be able to add with large positive integer (9 digits)
    Given I want check the scenario <context>
    When I do the math <value1> <operator> by <value2>
    Then I should see the result is <result>
    Examples:
      | context                             | value1    | operator | value2    | result      |
      | add two large positive integers     | 300000000 | add      | 900000000 | 1.2e+9      |
      | add a max integer and a min integer | 999999999 | add      | 1         | 1e+9        |
      | add a max integer and zero          | 999999999 | add      | 0         | 999999999   |
      | add two max integers                | 999999999 | add      | 999999999 | 2e+9        |

  @addition
  Scenario Outline: User should be able to add a number to the results of a previous operation
    Given I want check the scenario <context>
    When I do the chain math <value1> <operator1> by <value2> and <operator2> by <value3>
    Then I should see the result is <result>
    Examples:
      | context                                                                     | value1 | operator1 | value2 | operator2 | value3     | result      |
      | add a positive integer to the results of a previous operation               | 1500   | add       | -2000  | add       | 500        | 0           |
      | add a positive floating point number to the results of a previous operation | 1500   | add       | -2000  | add       | 0.25       | -499.75     |
      | add a floating point number with many decimal places to a previous result   | 1500   | add       | -2000  | add       | 1.23456789 | -498.765432 |
      | add a large integer to a previous result                                    | 1500   | add       | -2000  | add       | 123456789  | 123456289   |