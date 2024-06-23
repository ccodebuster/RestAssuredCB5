package com.student.responsetime;


/*---Interface ResponseOptions--
This interface contains four methods:-

 1. getTime() – The response time in milliseconds (or -1 if no response time could be measured)
 2. getTimeIn(TimeUnit timeunit) – The response time in the given time unit (or -1 if no response time could be measured)
 3. time() – The response time in milliseconds (or -1 if no response time could be measured)
 4. timeIn( TimeUnit timeunit ) – The response time in the given time unit (or -1 if no response time could be measured)
Technically, getTime() and time() both are the same, and getTimeIn() and timeIn() both are the same.*/

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class ResponseTime {
    @Test
    public void getResponseTime() {

        RequestSpecification requestSpecification = given();

        // Calling GET method
        Response response = requestSpecification.get("https://reqres.in/api/users/2");

        // Let's print response body.
        String resString = response.prettyPrint();
        System.out.println("Response Details : " + resString);

        //Get Response Time
        System.out.println("Response Time in milliseconds: " + response.getTime());

        System.out.println("Response Time in seconds: " + response.getTimeIn(TimeUnit.SECONDS));

        System.out.println("Response Time in milliseconds: " + response.time());

        System.out.println("Response Time in seconds: " + response.timeIn(TimeUnit.SECONDS));

    }

    /*Interface ValidatableResponseOptions
    * This interface has overloaded time() methods that accept Matcher.
 1. time(Matcher matcher) – Validate that the response time (in milliseconds) matches the supplied matcher.
 2. time(Matcher macther, TimeUnit timeunit) – Validate that the response time matches the supplied matcher and time unit.*/

    @Test
    public void verifyResponseTime() {

        // Given
        given()

                // When
                .when()
                .get("https://reqres.in/api/users/2")

                // Then
                .then()
                .statusCode(200).statusLine("HTTP/1.1 200 OK")

                // Asserting response time is less than 2000 milliseconds
                .time(Matchers.lessThan(3000L));

    }

    //Similarly, we can use greaterthan() method too.

    @Test
    public void verifyGreaterResponseTime() {

        // Given
        given()

                // When
                .when()
                .get("https://reqres.in/api/users/2")

                // Then
                .then()
                .statusCode(200).statusLine("HTTP/1.1 200 OK")

                // Asserting response time is greater than 3000 milliseconds
                .time(Matchers.greaterThan(2000L));
    }

    //If you want to verify the time range, it can also be done using the Matchers. Below is an example of the same.

    @Test
    public void verifyResponseTimeRange() {

        // Given
        given()

                // When
                .when()
                .get("https://reqres.in/api/users/2")

                // Then
                .then()
                .statusCode(200).statusLine("HTTP/1.1 200 OK")

                // Asserting response time is greater than 1000 milliseconds and less than 2000 milliseconds
                .time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
    }
}
