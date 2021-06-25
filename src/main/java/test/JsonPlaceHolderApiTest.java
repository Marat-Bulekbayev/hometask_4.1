package test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class JsonPlaceHolderApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users";
    }

    @Test
    public void verifyHttpStatusCode() {
        given().get().then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void verifyHttpResponseHeader() {
        given().get().then().assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void verifySizeOfResponseBodyArray() {
        given().get().then().assertThat().body("size()", is(10));
    }
}
