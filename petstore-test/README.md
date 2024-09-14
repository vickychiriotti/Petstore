# Petstore

Automation project with Gherkin, Cucumber, Java, and RestAssured to test the Petstore on localhost.
The project is structured into folders according to features, step definitions, and models for creating objects needed for validations. In CommonSteps.java, steps are defined that can be reused across different features. Each feature has its own step definitions to keep the code readable.

To run all the tests, you can use: mvn clean verify

Some tests fail due to bugs, such as the Scenario: Try to Delete an order by Id / Invalid ID supplied in the store.feature.
