Feature: creates a product


    Background:
      Given base url "https://backend.cashwise.us/api/myaccount"

@createProduct
  Scenario: user successfully creates a product:
    And I have access
    And I have the endpoint "/products"
    And I have "product_title" with "manty" in request body
    And I have "product_price" with "2.99" in request body
    And I have "service_type_id" with "1" in request body
    And I have "category_id" with "1" in request body
    And I have "product_description" with "manty" in request body
    And I have "date_of_payment" with "2024-05-29" in request body
    And I have "remind_before_day" with "2" in request body
    And I have "do_remind_every_month" with "REPEAT_EVERY_MONTH" in request body
    When I send post request
    Then verify status code is 201
    And verify I have "product_title" with "manty" in request body
    Then I delete the product










#  "product_title": "string",
#  "product_price": 0,
#  "service_type_id": 0,
#  "category_id": 0,
#  "product_description": "string",
#  "date_of_payment": "2024-06-07",
#  "remind_before_day": 0,
#  "do_remind_every_month": "REPEAT_EVERY_MONTH"
