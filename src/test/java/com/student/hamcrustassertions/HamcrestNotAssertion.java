package com.student.hamcrustassertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/*The not assertion inverts the meaning of the other assertions.
For example, if you want to perform negative assertions, then we can use any assertions with NOT.*/

public class HamcrestNotAssertion {
    public String endpoint = "https://restful-booker.herokuapp.com/booking/1";

    @Test
    public void negativeAssertions() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get(endpoint)
                .then().body("totalprice",not(equalTo(874)));

    }
}
