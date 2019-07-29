Feature: Make an order in 1a web-shop

  Scenario Outline: Successful order creation
    Given user navigates to homepage
    And enters <product name> into search bar
    And selects <category>
    And filters products by start rating
    And chooses product from search results
    And adds it to cart
    Then navigates to Cart
    And proceeds to checkout without registration
    And picks <delivery method>
    Then enters user data
      | name    | Neo           |
      | surname | Matrix        |
      | phone   | 23456789      |
      | email   | neo@matrix.lv |
    And sets <newsletter settings>
    And agrees with terms and proceeds to payment method
    And checks <payment method>
    Then completes the order
    Examples:
      | product name | category          | delivery method                                  | newsletter settings | payment method                         |
      | Lenovo       | Monitori          | Pasūtījumu saņemšu 1a.lv klientu centrā URIEKSTE | Uz e-pastu          | Pārskaitījums (Fiziska persona)        |
      | HP           | Portatīvie datori | Pasūtījumu saņemšu 1a.lv klientu centrā MŪKUSALA | Nevēlos saņemt      | Bankas karte (Online) (Fiziska persona) |