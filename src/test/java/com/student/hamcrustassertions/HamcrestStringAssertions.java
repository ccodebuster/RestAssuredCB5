package com.student.hamcrustassertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

/* 1. equalTo – It checks whether the extracted string from JSON is equal to the expected string.
   2. equalToIgnoringCase – It checks whether the extracted string from JSON is equal to the expected string without considering the case (small or capital).
   3. equalToIgnoringWhiteSpace – It checks whether the extracted string from JSON is equal to the expected string by considering the white spaces.
   4. containsString – It checks whether the extracted string from JSON contains the expected string as a substring.
   5. startsWith – It checks whether the extracted string from JSON is starting with a given string or character.
   6. endsWith – It checks whether the extracted string from JSON is ending with a given string or character.*/
public class HamcrestStringAssertions {
    /*{
    "firstname": "Eric",
    "lastname": "Wilson",
    "totalprice": 720,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2016-04-26",
        "checkout": "2022-06-24"
    }
}*/

    public String endpoint = "https://restful-booker.herokuapp.com/booking/1";

    @Test
    public void stringAssertions() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("firstname",equalTo("Eric"));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("firstname",equalToIgnoringCase("Eric"));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("firstname",containsString("Eric"));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("firstname",startsWith("E"));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("firstname",endsWith("c"));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("firstname",equalToIgnoringWhiteSpace("   Eric "));


    }
}
