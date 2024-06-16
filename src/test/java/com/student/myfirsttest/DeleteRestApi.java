package com.student.myfirsttest;


/*An HTTP DELETE method is used to delete an existing resource from the collection of resources.
The DELETE method requests the origin server to delete the resource identified by the Request-URI. On successful deletion of a resource,
it returns  200 (OK) and 204 (No Content) status codes. It may return as 202 (Accepted) status code if the request is queued. */

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteRestApi {

    /*{
            "id": 3,
            "employee_name": "Ashton Cox",
            "employee_salary": 86000,
            "employee_age": 66,
            "profile_image": ""
        }*/
    RequestSpecification requestSpecification;

    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void deleteUser() {


        RestAssured.baseURI = "https://dummy.restapiexample.com/api";

        // Create a request specification
        requestSpecification = given();

        // Calling DELETE method
        response = requestSpecification.delete("/v1/delete/3");

        // Let's print response body.
        String resString = response.prettyPrint();

        /*
         * To perform validation on response, we need to get ValidatableResponse type of
         * response
         */
        validatableResponse = response.then();

        // Get status code
        validatableResponse.statusCode(200);

        // It will check if status line is as expected
        validatableResponse.statusLine("HTTP/1.1 200 OK");

        // Check response - message attribute
        validatableResponse.body("message", equalTo("Successfully! Record has been deleted"));

    }

    @Test
    public void deleteUserBdd() {

        validatableResponse = given()
                .baseUri("https://dummy.restapiexample.com/api/v1/delete/3")
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .assertThat().statusCode(200)
                .body("message", equalTo("Successfully! Record has been deleted"));

        System.out.println("Response :" + validatableResponse.extract().asPrettyString());

    }
}
