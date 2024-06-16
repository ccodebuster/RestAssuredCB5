package com.student.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PracticeTest {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    @Test
    public void getAllBookingID(){

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200);

    }

    @Test
    public void getBookingID(){

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/53")
                .then()
                .statusCode(200)
                .body("lastname", equalTo("Smith"))
                .body("firstname", equalTo("John"));

    }

    @Test
    public void getAllBookingUsingRequestSpecification(){
/*
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200);*/

        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";

        requestSpecification = given();
       response =requestSpecification.get();
        validatableResponse = response.then();
        validatableResponse.statusCode(200);


    }

}
