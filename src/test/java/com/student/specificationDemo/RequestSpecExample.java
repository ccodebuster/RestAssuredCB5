package com.student.specificationDemo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecExample {

    RequestSpecBuilder builder;
    RequestSpecification requestSpecification;
    @BeforeClass

    public void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json");
        builder.addQueryParam("$limit", 2);
        requestSpecification = builder.build();


      /*  requestSpecification= RestAssured.given().header("Content-Type", "application/json")
                .queryParams("$limit", 2);
*/
    }
    @Test
    public void test001(){
      /* requestSpecification
                .get("/products")
                .then().log().all();*/

        given()
                .log().all()
                .spec(requestSpecification)
                .when()
                .get("/products")
                .then().log().all();
    }

    //without request specification
    @Test
    public void test002(){
        given().log().all()
                .header("Content-Type", "application/json")
                .queryParams("$limit", 2)
                .when()
                .get("http://localhost:3030/products")
                .then().log().all();
    }



}
