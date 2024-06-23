package com.student.logging;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*If you want to print the response body regardless of the status code, you can do
get("your endpoint ").then().log().body()..*/

public class ResponseLogging {
    @Test
    public void responseLoggingDemo() {

        String json = "{\"name\":\"apitest\",\"salary\":\"5000\",\"age\":\"30\"}";

        // GIVEN
        given()
                .baseUri("https://dummy.restapiexample.com/api")
                .contentType(ContentType.JSON)
                .body(json)

                // WHEN
                .when()
                .post("/v1/create")

                // THEN
                .then()
                .log().all()
                .statusCode(200)
                .body("data.name", equalTo("apitest"))
                .body("message", equalTo("Successfully! Record has been added."));

    }
}
