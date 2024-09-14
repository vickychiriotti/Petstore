@Pet
Feature: Pet
  @sanity
  Scenario Outline: Get a pet detail by petId
    Given The API endpoint method is /pet
    When I make a GET request with <petId>
    Then The response should have a <statusCode> status code
    And The response contain Pet detail
    Examples:
      | petId  | statusCode  |
      | 1      |   200       |
      | 2      |   200       |
      | 3      |   200       |
      | 4      |   200       |
      | 5      |   200       |
      | 6      |   200       |
      | 7      |   200       |
      | 8      |   200       |
      | 9      |   200       |
      | 10     |   200       |

  @405 @error
  Scenario Outline: Try to Get a pet by petId / Invalid ID supplied
    Given The API endpoint method is /pet
    When I make a GET request with <petId>
    Then The response should have a <statusCode> status code
    Examples:
      | petId  | statusCode  |
      | 345      |   404       |
      | 2567     |   404       |

  @sanity
  Scenario Outline: Get pets detail by status
    Given The API endpoint method is /pet/findByStatus
    When I make a GET request with <status> value
    Then The response should have a <statusCode> status code
    And The response contain Pets detail
    Examples:
      | status     | statusCode  |
      | available  |   200       |
      | pending    |   200       |
      | sold       |   200       |

  @sanity
  Scenario Outline: Get a pet detail by tags
    Given The API endpoint method is /pet/findByTags
    When I make a GET request with <tag> value
    Then The response should have a <statusCode> status code
    And The response contain Pets detail
    Examples:
      | tag     | statusCode  |
      | tag1    |   200       |
      | tag2    |   200       |
      | tag3    |   200       |

  @sanity @AddPet
  Scenario Outline: Add a new pet
    Given The API endpoint method is /pet
    When I create a new Pet with <id>, <name>, <categoryName>, <tagId> and <status>
    And I send a POST with the Pet
    Then The response should have a 200 status code
    And The added <name> pet has the <status> value in the response body
    Examples:
      | id | name       | categoryName | tagId | status    |
      | 10 | Milo       | Dogs          |1     | available |
      | 11 | Garfield   | Cats          |2     | pending   |


  @sanity @UpdatePet
  Scenario Outline: Update a pet in the store
    Given The API endpoint method is /pet
    When I update <id> pet with send a POST with <name> and <status> values
    Then The response should have a 200 status code
    And The updated <name> pet has the <status> value in the response body
    Examples:
      | id | name  | status    |
      | 10 | Messi | available |

  @404 @error
  Scenario Outline: Try to find a pet that doesn't exist
    Given The API endpoint method is /pet
    When I make a GET request with <petId>
    Then The response should have a <statusCode> status code
    Examples:
      | petId  | statusCode  |
      | 145      |   404       |
      | 154      |   404       |