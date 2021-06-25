package test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.core.Is.is;

public class JsonPlaceHolderApiTest {

    private static final String BASE_PATH = "/users";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void verifyHttpStatusCode() {
        given().get(BASE_PATH).then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void verifyHttpResponseHeader() {
        given().get(BASE_PATH).then().assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void verifyHttpResponseBody() {
        given().get(BASE_PATH).then().assertThat().body(matchesJsonSchemaInClasspath("schemas/users-schema.json")).and().body("size()", is(10));
    }
}
