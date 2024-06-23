package com.student.crudoperation;

import com.student.model.StudentPojo;
import com.student.testbase.TestBase;
import com.student.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StudentCrud extends TestBase {

   static String firstName = "bhave"+ TestUtils.getRandomValue();
    static String lastname = "patel" + TestUtils.getRandomValue();
    static String programme = "selenium testing";
    static String email = TestUtils.getRandomValue()+ "@gmail.com";
    static int studentId;

    @Test
    public void getStudentInfo(){
        given()
                .when()
                .get("/list")
                .then().statusCode(200);
    }

    @Test
    public void getStudentInfoById(){
        //http://localhost:8080/student/3
        Response response =given().log().all()
                .pathParam("id","3")
                .when()
                .get("/{id}");
        response.then().statusCode(200);

    }


    //create student data
    @Test
    public void test001(){
/*
        given().log().all()
                .when()
                .body(" {\n" +
                        "        \n" +
                        "        \"firstName\": \"bhavesh\",\n" +
                        "        \"lastName\": \"patel\",\n" +
                        "        \"email\": \"egestas.Proiin@massaQuisqueporttitor.org\",\n" +
                        "        \"programme\": \"Financial Analysis\",\n" +
                        "        \"courses\": [\n" +
                        "            \"Accounting\"\n" +
                        "          \n" +
                        "        ]\n" +
                        "    }")
                .post()
                .then().log().all()
                .statusCode(200);*/

        List<String>courseList = new ArrayList<>();
        courseList.add("selenium");
        courseList.add("cypress");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastname);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response =given().log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(studentPojo)
                .post();
        response.then().statusCode(201);

    }

    // extract student data
    @Test
    public void test002(){

        HashMap<String, Object> studentData =given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract().path("findAll{it.firstName =='"+firstName+"'}.get(0)");

        studentId= (int) studentData.get("id");
        System.out.println(studentId);
    }

    //getstudent by id to verify dats has been created
    @Test
    public void test003(){

        Response response =given().log().all()
                .pathParam("id",studentId)
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200).body("firstName", equalTo(firstName));

    }

    //delete by id
    @Test
    public void test004(){

        Response response =given().log().all()
                .pathParam("id",studentId)
                .when()
                .delete("/{id}");
        response.then().log().all().statusCode(204);

    }

    //get student by id to verify data has been deleted
    @Test
    public void test005(){

        Response response =given().log().all()
                .pathParam("id",studentId)
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(404);

    }

}
