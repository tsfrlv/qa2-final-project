Feature: Make an order in 1a web-shop

  Scenario Outline: Successful order creation
    Given user navigates to homepage
    And enters product name into search bar
    And selects a category
    And filters products by start rating
    And selects product
    And adds it to cart
    Then navigates to Cart
    And proceeds to checkout without registration
    And selects <delivery method>
    Then enters user data
      | name    | Neo           |
      | surname | Matrix        |
      | phone   | 23456789      |
      | email   | neo@matrix.lv |
    And agrees with terms
    And sets <newsletter settings>
    And selects to <payment method>
    Then completes the order
    Examples:
      | delivery method                                  | newsletter settings | payment method |
      | Pasūtījumu saņemšu 1a.lv klientu centrā URIEKSTE | Uz e-pastu          | Skaidra nauda  |
      | Pasūtījumu saņemšu 1a.lv klientu centrā MŪKUSALA | Nevēlos saņemt      | Norēķinu karte |
