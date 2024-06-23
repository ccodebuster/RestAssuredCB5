package com.student.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostApi {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;


    @Test
    public void createNewEmployee() {

        given().log().all()
                .when()
                .body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
                .post("https://dummy.restapiexample.com/api/v1/create")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void verifyStatusCode() {
        //ValidatableResponse for the assertion of status and status line of the Response.

        String employeeData = "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}";

        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/create";

        // Create a request specification
        requestSpecification = RestAssured.given().contentType(ContentType.JSON);

       // requestSpecification.contentType(ContentType.JSON);

        requestSpecification.body(employeeData);
        // Calling GET method
        response = requestSpecification.post();

        // Let's print response body.
        String resString = response.prettyPrint();
        System.out.println("Response Details : " + resString);

        /*
         * To perform validation on response, we need to get ValidatableResponse type of
         * response
         */
        validatableResponse = response.then();

        // Get status code
        validatableResponse.statusCode(200);

        // Check status line is as expected
        validatableResponse.statusLine("HTTP/1.1 200 OK");

        validatableResponse.body("message", equalTo("Successfully! Record has been added."));
    }


    @Test
    public void createUserBdd() {

        String json = "{\"name\":\"bhavesh\",\"salary\":\"5000\",\"age\":\"30\"}";

        // GIVEN
        validatableResponse = given()
                .baseUri("https://dummy.restapiexample.com/api")
                .contentType(ContentType.JSON)
                .body(json)

                // WHEN
                .when()
                .post("/v1/create")

                // THEN
                .then()
                .assertThat().statusCode(200).body("data.name", CoreMatchers.equalTo("bhavesh"))
                .body("message", CoreMatchers.equalTo("Successfully! Record has been added."));

        System.out.println("Response :" + validatableResponse.extract().asPrettyString());
    }
}
