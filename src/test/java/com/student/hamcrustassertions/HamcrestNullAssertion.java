package com.student.hamcrustassertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/*nullValue – It checks whether the extracted response from JSON is NULL or Not.*/

public class HamcrestNullAssertion {
    public String endpoint = "https://restful-booker.herokuapp.com/booking/1";

    @Test
    public void nullAssertion() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("totalprice1", is(nullValue()));
    }
}
