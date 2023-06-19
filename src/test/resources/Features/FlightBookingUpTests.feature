Feature: UI testing for Declined payment Form

  Scenario: Choose any flight from Vilnius for one person and try to pay with wrong card data
    Given Set rid cookie
    And Navigate to main page
    And Select flight to anywhere from Vilnius in current month
    And Open first possible destination country
    And Open first possible destination city
    And Open first possible flight and proceed to checkout
    And Fill out login with userName "vyzudet@givmail.com" and password "zaqmjyR!5" and press login
    When Fill out fields on checkout page and press pay
      | phoneNumber | cardNumber       | expiryDate | securityCode | cardholderName | firstAddress | secondAddress | city      | country | postcode |
      | 1111111111  | 5982761200000006 | 0125       | 123          | John Doe       | Nathan Rd    | Kwun Chung    | Hong Kong | China   | 853CWM   |
    Then Payment error is displayed

  Scenario: Choose any flight from specific city on specific month and day for one person and try to pay with wrong card data
    Given Set rid cookie
    And Navigate to main page
    And Select flight to anywhere in month and day of week
      | month | dayOfWeek  | departureCity |
      | Sep   | Tuesday    | Dublin        |
    And Open first possible destination country
    And Open first possible destination city
    And Open first possible flight and proceed to checkout with passenger info
      | firstName | lastName  | title |
      | John      | Doe       | Mr    |
    And Fill out login with userName "vyzudet@givmail.com" and password "zaqmjyR!5" and press login
    When Fill out fields on checkout page and press pay
      | phoneNumber | cardNumber       | expiryDate | securityCode | cardholderName | firstAddress | secondAddress | city      | country | postcode |
      | 1111111111  | 5982761200000006 | 0125       | 123          | John Doe       | Nathan Rd    | Kwun Chung    | Hong Kong | China   | 853CWM   |
    Then Payment error is displayed