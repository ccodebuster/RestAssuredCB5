package com.student.logging;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*To log all request specification details including parameters, headers, and body of the request, log().all() needs to be added to post given() section.*/

public class RequestLogging {
    @Test
    public void requestLoggingDemo() {

        String json = "{\"name\":\"apitest\",\"salary\":\"5000\",\"age\":\"30\"}";

        // GIVEN
        given()
                .log().all()
                .baseUri("https://dummy.restapiexample.com/api")
                .contentType(ContentType.JSON)
                .body(json)

                // WHEN
                .when()
                .post("/v1/create")

                // THEN
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.name", equalTo("apitest"))
                .body("message", equalTo("Successfully! Record has been added."));

    }

    /*Other different request logging options are:-
 1. given().log().params(). .. // Log only the parameters of the request
 2. given().log().body(). .. // Log only the request body
 3. given().log().headers(). .. // Log only the request headers
 4. given().log().cookies(). .. // Log only the request cookies
 5. given().log().method(). .. // Log only the request method
 6. given().log().path(). .. // Log only the request path*/

}
