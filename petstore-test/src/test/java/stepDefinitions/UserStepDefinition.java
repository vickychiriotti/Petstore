package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.Getter;

import static io.restassured.RestAssured.given;

public class UserStepDefinition {

    @Getter
    private Response response = CommonSteps.getResponse();
    private String url =   CommonSteps.url;

    @When("I make a GET request with {word} username and {word} password value")
    public void iMakeAGETRequestWithUsernameAndPassword(String username, String password) {
        response = given()
                .header("Accept", "application/json")
                .when()
                .param("login", username)
                .param("password", password)
                .get(url);
        System.out.println("Response: " + response.getBody().asString());
    }

    @And("It should show {string} message")
    public void itShouldShowMessage(String message) {
        response.then().assertThat().toString().contains(message);
    }

    @Then("The user response should have a {int}")
    public void theUserResponseShouldHave(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
}
