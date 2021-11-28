@mystore-testlab
Feature: Change user information after login and shopping

  @create_new_address_parametr
  Scenario Outline: Create new address
    Given An open browser with mystore-testlab.coderslab.pl
    When A button 'Sign in' is clicked
    And An e-mail and a password is entered in input fields
    And A button 'SIGN IN' is clicked
    And User is logged
    And An link 'Addresses' is clicked
    And An link 'Create new address' is clicked
    And An alias <alias>, an address <address>, a city <city>, zip/postal code <zip/postal code>, a country <country> and a phone <phone> is entered in input fields
    And A button 'SAVE' is clicked
    Then A new address <alias>, <address>, <city>, <zip/postal code>, <country>, <phone> is added
    And A success message is displayed
    And Close browser

    Examples:
      |alias       |address       |city     |zip/postal code|country       |phone    |
      |Mój adres   |Długa 72      |Frombork |12-456         |United Kingdom|600600600|
      |Adres domowy|Floriańska 8  |Jastarnia|00-999         |United Kingdom|500500500|
      |Domek       |Elektryczna 65|Grajewo  |44-000         |United Kingdom|888777666|
      |Home        |Jesionowa 3   |Andrychów|65-321         |United Kingdom|600500400|

  @delete_an_address_parametr
  Scenario Outline: Delete an address
    Given An open browser with mystore-testlab.coderslab.pl
    When A button 'Sign in' is clicked
    And An e-mail and a password is entered in input fields
    And A button 'SIGN IN' is clicked
    And User is logged
    And An link 'Addresses' is clicked
    And An link 'Delete' is clicked
    Then A success delete message is displayed
    And Close browser

  @shopping
  Scenario Outline: Shopping on mystore
    Given An open browser with mystore-testlab.coderslab.pl
    When A button 'Sign in' is clicked
    And An e-mail and a password is entered in input fields
    And A button 'SIGN IN' is clicked
    And User is logged
    And A keyword 'Hummingbird Printed Sweater' is entered in input field
    And The first one should contain 'Hummingbird Printed Sweater'
    And The first one is clicked
    And Check if the discount is 20%
    And A size 'M' is chosen from selection list
    And Quantity '5' is entered in input field
    And A button 'ADD TO CART' is clicked
    And A button 'PROCEED TO CHECKOUT' is clicked
    Then /A new address <alias>, <address>, <city>, <zip/postal code>, <country>, <phone> is added
    And /A success message is displayed
    Then Close browser