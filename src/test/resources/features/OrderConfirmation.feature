Feature: Order Confirmation

  Background:
    Given the user is logged in
    And the user has "Sauce Labs Bike Light" in the cart
    And the user opens the shopping cart
    And the user clicks the Checkout button
    And the user enters first name "Shashwat", last name "Dodamani", and postal code "560001"
    And the user clicks the Continue button
    And the user clicks the Finish button

  Scenario: Verify the order confirmation page
    Then the order confirmation page title should be "Checkout: Complete!"
    And the order confirmation header should be "Thank you for your order!"
    And the Pony Express section should be displayed
    And the Back Home button should display "Back Home"
    And the Generate PDF button should display "Generate PDF order"

  Scenario: Verify the Back Home button navigates to the Products page
    When the user clicks the Back Home button
    Then the Products page should be displayed

  Scenario: Verify the Generate PDF Order button can be clicked
    When the user clicks the Generate PDF Order button
    Then the Generate PDF Order button should remain displayed