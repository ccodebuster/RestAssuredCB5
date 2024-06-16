package com.student.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PutRestApi {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void PutNewEmployee() {

        given()
                .when()
                .body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
                .put("https://dummy.restapiexample.com/api/v1/update/21")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void updateUser() {

    /*{
            "id": 2,
            "employee_name": "Garrett Winters",
            "employee_salary": 170750,
            "employee_age": 63,
            "profile_image": ""
        }*/
        //change the employee_salary to 99999.

        String jsonString = "{\"id\": 2,\r\n"
                + "        \"employee_name\": \"Garrett Winters\",\r\n"
                + "        \"employee_salary\": 99999,\r\n"
                + "        \"employee_age\": 63,\r\n"
                + "        \"profile_image\": \"\"}";

        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/update/2";

        // Create a request specification
        requestSpecification = given().contentType(ContentType.JSON);

        // Setting content type to specify format in which request payload will be sent.
        //requestSpecification.contentType(ContentType.JSON);

        // Adding body as string
        requestSpecification.body(jsonString);

        // Calling PUT method
        response = requestSpecification.put();

        // Let's print response body.
        String responseString = response.prettyPrint();

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
        validatableResponse.body("message", equalTo("Successfully! Record has been updated."));

    }

    //let us convert the same test into BDD format.

    @Test
    public void updateUserBdd() {

        // To get the detail of employee with id 2
        validatableResponse = given()
                .baseUri("https://dummy.restapiexample.com/api/v1/employee/2")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .assertThat().statusCode(200);

        System.out.println("Response1 :" + validatableResponse.extract().asPrettyString());

        String jsonString = "{\"id\": 2,\r\n"
                + "        \"employee_name\": \"Garrett Winters\",\r\n"
                + "        \"employee_salary\": 99999,\r\n"
                + "        \"employee_age\": 63,\r\n"
                + "        \"profile_image\": \"\"}";

        // Update employee_salary
        validatableResponse = given()
                .baseUri("https://dummy.restapiexample.com/api/v1/update/2")
                .contentType(ContentType.JSON)
                .body(jsonString)
                .when()
                .put()
                .then()
                .statusCode(200)
                .body("message", equalTo("Successfully! Record has been updated."));

        System.out.println("Response2 :" + validatableResponse.extract().asPrettyString());

    }
}
