package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.Getter;
import model.Order;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static stepDefinitions.CommonSteps.url;

public class StoreSteps {

    @Getter
    private Order order;
    private Response response = CommonSteps.getResponse();

    @And("I send a POST with an order")
    public void iSendAPOSTWithAnOrder() {
        response = given()
                .contentType("application/json")
                .accept("application/xml")
                .body(order)
                .when()
                .post(url);
        System.out.println(response.getBody().asString());
    }

    @When("I create an order with {long}, {long}, {int}, {word}, {word}, {word}")
    public void iCreateAnOrderWith(long id, long petID, int quantity, String shipDate, String status, String complete) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC);
        Instant instant = Instant.from(formatter.parse(shipDate));
        Date date = Date.from(instant);

        order = Order.builder()
                .id(id)
                .petId(petID)
                .quantity(quantity)
                .shipDate(date)
                .status(status)
                .complete(Boolean.parseBoolean(complete))
                .build();
    }

    @Then("The store response should have a {int}")
    public void theStoreResponseShouldHave(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
}
