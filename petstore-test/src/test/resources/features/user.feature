@All
@User
Feature: User

  @Login
  Scenario: Logs user into the system
    Given The API endpoint method is /user/login
    When I make a GET request with Victoria username and 123 password value
    Then The user response should have a 200
    And It should show "Logged in user session:" message

  @Logout
  Scenario: Logs out current logged in user session
    Given The API endpoint method is /user
    When I make a GET request with logout
    Then The user response should have a 200
    And It should show "User logged out" message