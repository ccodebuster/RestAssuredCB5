package com.student.hamcrustassertions;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HamcrustMuliplteAssertions {
    public String endpoint = "https://restful-booker.herokuapp.com/booking/1";

    @Test
    public void test1() {
        given().log().all().contentType(ContentType.JSON)
                .when().get(endpoint).then().log().all()
                .body("firstname", equalTo("Susan"), "lastname", equalTo("Brown"), "totalprice", equalTo(552));
    }

    @Test // assertion fail and program terminate
    public void test2() {
        given().contentType(ContentType.JSON)
                .when().get(endpoint).then()
                .body("firstname", Matchers.equalTo("Sally"))
                .body("lastname", Matchers.equalTo("Jackson"))
                .body("totalprice", Matchers.equalTo(949)); // will fail
    }

    @Test
    public void verifySoftAssertion() {

        // Given
        given()

                // When
                .when()
                .get("https://reqres.in/api/users/2")

                // Then
                .then()

                // To verify the response body
                .body("data.email", Matchers.equalTo("janet.weaver@reqres12.in"),
                        "data.first_name", Matchers.equalTo("Janet1"),
                        "data.last_name", Matchers.equalTo("Weaver"));

    }

    @Test
    public void verifyHardAssertion() {

        // Given
        given()

                // When
                .when()
                .get("https://reqres.in/api/users/2")

                // Then
                .then()

                // To verify the response body
                .body("data.email", Matchers.equalTo("janet.weaver@reqres12.in"))
                .body("data.first_name", Matchers.equalTo("Janet1"))
                .body("data.last_name", Matchers.equalTo("Weaver"));

    }
}
