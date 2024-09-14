package stepDefinitions;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Category;
import model.Pet;
import model.Tag;

import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static stepDefinitions.CommonSteps.url;

public class PetSteps {

    private Pet pet;
    private Pet[] pets;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private Response response = CommonSteps.getResponse();

    @Then("The response should have a {word} status code")
    public void theResponseShouldHaveAStatusCode(String expectedStatusCode) {
        response.then().assertThat().statusCode(Integer.parseInt(expectedStatusCode));
        System.out.println("response.then().statusCode(Integer.parseInt(expectedStatusCode)): " + response.then().statusCode(Integer.parseInt(expectedStatusCode)));

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @And("The response contain Pet detail")
    public void theResponseContainPetDetail() throws JsonProcessingException {
        String responseBody = response.getBody().asString();
        Pet pet = OBJECT_MAPPER.readValue(responseBody, Pet.class);
        assert pet != null;
    }

    @And("The response contain Pets detail")
    public void theResponseContainPetsDetail() throws JsonProcessingException {
        String responseBody = response.getBody().asString();
        pets = OBJECT_MAPPER.readValue(responseBody, Pet[].class);
        assert pets != null;
    }

    @When("I create a new Pet with {long}, {word}, {word}, {long} and {word}")
    public void iCreateANewPetWith(long id, String name, String categoryName, long tagId, String status) {
        long categoryId = 1L;
        if (categoryName.equals("Dogs"))
            categoryId = 1;
        if (categoryName.equals("Cats"))
            categoryId = 1;

        pet = Pet.builder()
                .id(id)
                .name(name)
                .tags(Collections.singletonList(
                                Tag.builder()
                                        .name("tag1")
                                        .id(tagId)
                                        .build()
                        )
                )
                .category(Category.builder()
                        .id(categoryId)
                        .name(categoryName)
                        .build())
                .photoUrls(Arrays.asList("", ""))
                .status(status)
                .build();

    }

    @And("I send a POST with the Pet")
    public void iSendAPOSTWithThePet() {
        response = given()
                .contentType("application/json")
                .accept("application/xml")
                .body(pet)
                .when()
                .post(url);
        System.out.println(response.getBody().asString());
    }

    @When("I update {long} pet with send a POST with {word} and {word} values")
    public void iUpdateIdPetWithNameAndStatusValues(long id, String name, String status) throws JsonProcessingException {
        response = RestAssured.given()
                .accept("*/*")
                .queryParams("name", name, "status", status)
                .post(url + id);
        System.out.println("url:  " + url + id) ;
        System.out.println("response.getBody().asString():  " + response.getBody().asString());
        pet = OBJECT_MAPPER.readValue(response.getBody().asString(), Pet.class);
        assert pets != null;
    }

    @And("The added {word} pet has the {word} value in the response body")
    @And("The updated {word} pet has the {word} value in the response body")
    public void theUpdatedNamePetHasTheStatus(String name, String status) {
        assert pet.getName().equals(name);
        assert pet.getStatus().equals(status);
    }
}
