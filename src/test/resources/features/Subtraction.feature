@calculator
Feature: Test online calculator scenarios

  Background:
    Given I open chrome browser and start online calculator application

  @subtraction
  Scenario Outline: User should be able to subtract two numbers
    Given I want check the scenario <context>
    When I do the math <value1> <operator> by <value2>
    Then I should see the result is <result>
    Examples:
      | context                                                   | value1    | operator | value2    | result    |
      | subtract two positive integers                            | 2000      | subtract | 1500      | 500       |
      | subtract zero from a negative integer                     | -3        | subtract | 0         | -3        |
      | subtract zero from a positive integer                     | 3         | subtract | 0         | 3         |
      | subtract a floating point number from a negative integer  | -3        | subtract | 5.5       | -8.5      |
      | subtract an integer from a floating point number          | 9.35      | subtract | 1         | 8.35      |
      | subtract a floating point number from an integer          | 9         | subtract | 1.35      | 7.65      |
      | subtract two floating point numbers                       | 0.29      | subtract | 1.35      | -1.06     |
      | subtract two max-input floating point numbers             | 7.1234567 | subtract | 2.2109876 | 4.9124691 |
      | subtract an integer from a negative floating point number | -1.33     | subtract | 2         | -3.33     |

  @subtraction
  Scenario Outline: User should be able to subtract with large numbers (9 digits)
    Given I want check the scenario <context>
    When I do the math <value1> <operator> by <value2>
    Then I should see the result is <result>
    Examples:
      | context                                              | value1     | operator | value2     | result     |
      | subtract two large integers                          | 123456789  | subtract | 210987654  | -87530865  |
      | subtract two floating point numbers with many digits | 7.12345678 | subtract | 2.21098765 | 4.91246913 |

  @subtraction
  Scenario Outline: User should be able to subtract a number to the results of a previous operation
    Given I want check the scenario <context>
    When I do the chain math <value1> <operator1> by <value2> and <operator2> by <value3>
    Then I should see the result is <result>
    Examples:
      | context                                                                  | value1 | operator1 | value2 | operator2 | value3     | result      |
      | subtract an integer from the results of a previous operation             | 1500   | subtract  | 2000   | subtract  | 500        | -1000       |
      | subtract a floating point number from the result of a previous operation | 1500   | subtract  | 2000   | subtract  | 33.12      | -533.12     |
      | subtract a large decimal number from the results of a previous result    | 1500   | subtract  | 2000   | subtract  | 12.3456789 | -512.345679 |
      | subtract a large integer from the results of a previous result           | 1500   | subtract  | 2000   | subtract  | 123456789  | -123457289  |