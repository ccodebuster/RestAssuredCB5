package com.student.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRestApi {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllEmployeesWay1() {

        given().log().all()
                .when()
                .get("https://dummy.restapiexample.com/api/v1/employees")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode() {
        //ValidatableResponse for the assertion of status and status line of the Response.

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";

        // Create a request specification
        requestSpecification = RestAssured.given();


        // Calling GET method
        response = requestSpecification.get();

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
    }

    @Test
    public void verifyStatusCodeBdd() {
        //If you don’t want to use ValidatableResponse for the assertion,
        // you can use Response from io.restassured .response to get the status code and status line,
        // which are asserted using JUnit.Assert.

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";

        // Create a request specification
        requestSpecification = given();

        // Calling GET method
        response = requestSpecification.get();

        // Let's print response body.
        String resString = response.prettyPrint();
        System.out.println("Response Details : " + resString);

        // Get status line
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

        // Get status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void verifyUser() {

        given()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employees")
                .then()
                .statusCode(200)
                .body("data[7].employee_name", equalTo("Rhona Davidson"))
                .body("message", equalTo("Successfully! All records has been fetched."))
                .body("data[7].employee_age", equalTo(55));

    }

    @Test
    public void verifyUser1() {

        Response resposne = given()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employees");

        String name = resposne.then().extract().path("data[7].employee_name");
        String eName = "Rhona Davidson";

        Assert.assertEquals(name, eName);
    }

    @Test
    public void verifyUser2() {

        ValidatableResponse validatableResponse = given()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employees").then();

        String name = validatableResponse.extract().path("data[7].employee_name");
        String eName = "Rhona Davidson";

        int age = validatableResponse.extract().path("data[7].employee_age");
        System.out.println(age);
        int expectedAge = 55;

        Assert.assertEquals(age, expectedAge);

    }

    @Test
    public void getEmployeeById() {

        given()
                .when()
                .get("https://dummy.restapiexample.com/api/v1/employee/7365")
                .prettyPrint();
    }


    @Test
    public void getProducts() {

        given()
                .when()
                .get("http://localhost:3030/products")
                .then().log().all();
    }
}
