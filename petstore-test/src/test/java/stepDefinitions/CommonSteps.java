package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.Getter;

import static io.restassured.RestAssured.given;

public class CommonSteps {
    private String baseURI;
    static String url;

    @Getter
    private static Response response;
    private String method;
    private String param;

    @Before
    public void setup() {
        RestAssuredConfig.init();
        baseURI = "http://localhost:8080/api/v3";
    }

    @Given("The API endpoint method is {word}")
    public void theAPIEndpointMethod(String method) {
        url = baseURI + method + "/";
        this.method = method;
        System.out.println("url: " + url);
    }

    @When("I make a GET request with {word}")
    public void iMakeAGETRequestWith(String method) {
        this.method = method;
        response = given()
                .header("Accept", "application/json")
                .when()
                .get(url + method);
        System.out.println(response.getBody().asString());
    }

    @When("I make a GET request")
    public void iMakeAGETRequest() {
        response = given()
                .header("Accept", "application/json")
                .when()
                .get(url);
        System.out.println(response.getBody().asString());
    }

    @When("I make a DELETE request with {int} id")
    public void iMakeADELETERequest(int id) {
        response = given()
                .header("Accept", "application/json")
                .when()
                .delete(url + id);
        System.out.println(response.getBody().asString());
    }

    @When("I make a GET request with {word} value")
    public void iMakeAGETRequestWithParam(String value) {
        System.out.println("this.method: "+ this.method);
        if(this.method.contains("ByStatus"))
            param = "status";
        if(this.method.contains("ByTags"))
            param = "tags";

        response = given()
                .header("Accept", "application/json")
                .when()
                .param(param, value)
                .get(url);
        System.out.println(response.getBody().asString());
    }

}
