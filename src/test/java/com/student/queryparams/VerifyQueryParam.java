package com.student.queryparams;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyQueryParam {

    //https://reqres.in/api/users?page=2

    @Test
    public void queryParams() {

        given().log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void queryParam2() {

        String url = "https://reqres.in/api/users";


        given().log().all()
                .queryParams("page", "2")
                .when()
                .get(url)
                .then().log().all()
                .statusCode(200);
    }
}
