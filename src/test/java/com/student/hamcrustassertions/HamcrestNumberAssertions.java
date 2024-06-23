package com.student.hamcrustassertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

/*  1. equalTo – It checks whether the retrieved number from the response is equal to the expected number.
    2. greaterThan – checks extracted number is greater than the expected number.
    3. greaterThanOrEqualTo – checks whether the extracted number is greater than equal to the expected number.
    4. lessThan – It checks whether the retrieved number from the response is lesser than the expected number.
    5. lessThanOrEqualTo – It checks whether the retrieved number from the response is lesser than or equal to the expected number.*/
public class HamcrestNumberAssertions {
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
    public void numberAssertions() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint).then()
                .body("totalprice", equalTo(317));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("totalprice",greaterThan(100));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("totalprice",greaterThanOrEqualTo(50));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("totalprice",lessThan(1000));

        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("totalprice",lessThanOrEqualTo(1000));

    }
}
