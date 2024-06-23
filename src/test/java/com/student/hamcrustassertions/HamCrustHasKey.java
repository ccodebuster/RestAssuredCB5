package com.student.hamcrustassertions;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsMapContaining.hasKey;

public class HamCrustHasKey {

    String url ="https://restful-booker.herokuapp.com/booking/1";

    @Test
    public void collections(){

        given().log().all()
                .when()
                .get(url)
                .then().body("bookingdates",hasKey("checkin"));
    }
}
