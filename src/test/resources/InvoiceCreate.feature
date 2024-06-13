Feature: Create invoice

  Background:
    Given our base url "https://backend.cashwise.us/api/myaccount"


  @invoicePart

  Scenario: user successfully creates invoice

    And I got access
    And I got the endpoint "/invoices"
    And I got "invoice_title" with "Auto" in request body
    And I got "client_id" with "1" in request body
    And I got "date_of_creation" with "2024-06-09" in request body
    And I got "end_date" with "2024-06-11" in request body
    And I got "description" with "Get badass wheels for your vehicle" in request body
    And I got "product_title" with "Auto" in request body
    And I got "product_id" with "1" in request body
    And I got "product_price" with "180" in request body
    And I got "service_type_id" with "2" in request body
    And I got "category_id" with "1" in request body
    And I got "product_description" with "Auto" in request body
    And I got "sum" with "100" in request body
    And I got "discount" with "1" in request body
    And I got "invoice_title" with "Auto" in request body
    And I got "sum_of_discount" with "1" in request body
    When I will send post request
    Then Verify status code equals 201
    And Validate I got "invoice_title" with "Auto" in request body
    Then I remove the invoice




