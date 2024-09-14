package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;

public class RestAssuredConfig {
    public static void init() {

        RestAssured.config = RestAssured.config().objectMapperConfig(
                new ObjectMapperConfig(ObjectMapperType.JACKSON_2));
    }
}
