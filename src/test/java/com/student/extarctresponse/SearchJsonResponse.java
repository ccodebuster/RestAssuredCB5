package com.student.extarctresponse;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SearchJsonResponse {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().log().all()
                .when()
                .get("/products")
                .then().log().all().statusCode(200);

    }

    // Extract the value of limit
    @Test
    public void test001(){
    int actualLimit=response.extract().path("limit");
    int expectedLimit = 10;
    Assert.assertEquals(actualLimit,expectedLimit); // one way of assertion through extraction
    response.body("limit", equalTo(10)); //second way of assertion in to body

    }

    //Extract list of all id
    @Test
    public void test002(){
        List<Integer>listOfId=response.extract().path("data.id");
        System.out.println(listOfId);
        for(Integer id:listOfId){
            if(id.equals(150115)){
                Assert.assertTrue(true);
            }

        }

    }

    // 3) Extract first product name from data
    @Test
    public void test003() {
        String nameOfprd=response.extract().path("data[0].name");
        System.out.println(nameOfprd);

       /* List<String> dataNames = response.extract().path("data.name");
       // System.out.println("List of IDs within the limit : " + dataNames );
        System.out.println(dataNames.get(1));*/
    }

    @Test
    public void test004() {
        List<HashMap<Object, ?>> details=response.extract().path("data[0].categories");
        System.out.println(details);
    }

    @Test
    public void test005() {
        HashMap<Object, ?> details=response.extract().path("data[0].categories[0]");
        System.out.println(details);
    }

    @Test
    public void test006() {
        List<Object> dataSize=response.extract().path("data");
        System.out.println(dataSize.size());
    }

    //get all values of Duracell - AA Batteries (8-Pack)
    @Test
    public void test007() {
        List<HashMap<String,?>> values=response.extract().path("data.findAll{it.name= 'Duracell - AA Batteries (8-Pack)'}");
        System.out.println(values);

    }

    @Test
    public void test008() {
        Float price=response.extract().path("data.find{it.name= 'Duracell - AAA Batteries (4-Pack)'}.price");
        System.out.println(price);

    }

    // 9) Get the Names of products which have price less than 16.99
    @Test
    public void test009() {
        List<String>prdName=response.extract().path("data.findAll{it.price <16.99}.name");
        System.out.println(prdName);
    }

    // 10) Get the manufacturer of products whose name Start = Ene
    @Test
    public void test010() {
        List<?>menuName=response.extract().path("data.findAll{it.name.startsWith('Ene')}.manufacturer");
        System.out.println(menuName);
    }

    // 11) Get the price of products whose name end with = Vehicles
    @Test
    public void test011() {

        List<?>priceList=response.extract().path("data.findAll{it.name==~/.*Vehicles/}.price");
        List<?>priceList1=response.extract().path("data.findAll{it.name==~/.*Black/}.price");
        System.out.println(priceList);

    }


}
