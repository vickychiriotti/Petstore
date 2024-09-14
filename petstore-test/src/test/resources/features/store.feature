@All
@Store
Feature: Store

  @GetPetBySatus
  Scenario: Get pet inventories by status
    Given The API endpoint method is /store/inventory
    When I make a GET request
    Then The response should have a 200 status code

  Scenario Outline: Place an order for a pet
    Given The API endpoint method is /store/order
    When I create an order with <id>, <petId>, <quantity>, <shipDate>, <status>, <complete>
    And I send a POST with an order
    Then The store response should have a 200
    Examples:
    | id | petId   | quantity | shipDate                | status    | complete |
    | 10 | 198772  | 7        |2024-09-14T04:27:39.861Z | approved  | true     |
    | 12 | 198772  | 5        |2024-09-13T04:27:39.861Z | approved  | false     |

  @delete
  Scenario: Delete an order by Id
    Given The API endpoint method is /store/order
    When I make a DELETE request with 10 id
    Then The store response should have a 200

  @400 @error #There is a bug in this scenario because, for ID values above 1000, an error should be generated, but a 200 status is returned instead.
  Scenario: Try to Delete an order by Id / Invalid ID supplied
    Given The API endpoint method is /store/order
    When I make a DELETE request with 1002 id
    Then The store response should have a 400