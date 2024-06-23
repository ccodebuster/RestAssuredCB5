package com.student.crudoperation;

import com.student.model.Datum;
import com.student.model.Products;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductCrud {

    int idNumber;
    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test
    public void test001(){
       Products products = given()
                .when()
                .get()
               .getBody().as(Products.class);
        System.out.println(products.getTotal());

    }


    // create new product
    @Test
    public void test002(){

        Datum datum = new Datum();
        datum.setName("bhavesh");
        datum.setType("hghj");
        datum.setPrice(123F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");
        Datum datum1=given()
               .log().all()
               .header("Content-Type", "application/json")
               .when()
               .body(datum)
               .post()
               .getBody()
               .as(Datum.class);
        // Datum datum1=response.getBody().as(Datum.class);
        System.out.println(datum1.getId());
        idNumber=datum1.getId();

       /* Response response =given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(datum)
                .post();*/
       // int idNumber=response.then().extract().path("id");

    }

    // get by id
    @Test
    public void test003(){


        Datum datum1=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}")
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getName());

    }


    // update product
    @Test
    public void test004(){

        Datum datum = new Datum();
        datum.setPrice(123F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");

        Response response=given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .body(datum)
                .patch("/{id}");
        response.then().statusCode(200);

    }

    // delete by id
    @Test
    public void test005(){


        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);


    }

    // get by id after delete
    @Test
    public void test006(){

        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);

    }

}
