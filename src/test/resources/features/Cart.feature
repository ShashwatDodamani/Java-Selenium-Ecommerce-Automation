Feature: Shopping Cart

  Background:
    Given the user is logged in

  Scenario: Verify the selected product is displayed in the cart
    When the user adds the "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    Then the cart should display the product "Sauce Labs Bike Light"

  Scenario: Verify the product price is displayed in the cart
    When the user adds the "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    Then the cart should display the price "$9.99"

  Scenario: Verify the product description is displayed in the cart
    When the user adds the "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    Then the cart should display the description "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."

  Scenario: Verify user can remove a product from the cart
    When the user adds the "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    And the user removes the product from the cart
    Then the product should not be displayed in the cart

  Scenario: Verify user can navigate to the Checkout page from the cart
    When the user adds the "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    And the user clicks the Checkout button
    Then the Checkout page should be displayed

  Scenario: Verify user can continue shopping
    When the user adds the "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    And the user clicks the Continue Shopping button
    Then the Products page should be displayed

  Scenario: Verify multiple products are displayed in the cart
    When the user adds the following products to the cart:
      | Sauce Labs Bike Light |
      | Sauce Labs Backpack |
      | Sauce Labs Bolt T-Shirt |
    And the user opens the shopping cart
    Then the cart should contain the following products:
      | Sauce Labs Bike Light |
      | Sauce Labs Backpack |
      | Sauce Labs Bolt T-Shirt |