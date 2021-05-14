package get_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest06dt extends DummyBaseUrl {
/*
      When
            I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employee/8
      Then
          HTTP Status Code should be 200
          And response content type is “application/JSON”
          And "Connection" in Headers should be "keep-alive"
          And response body should be like;
                                     {
                                "status": "success",
                                "data": {
                                    "id": 8,
                                    "employee_name": "Rhona Davidson",
                                    "employee_salary": 327900,
                                    "employee_age": 55,
                                    "profile_image": ""
                                },
                                "message": "Successfully! Record has been fetched."
                            }
 */

    @Test
    public void get01(){
        //1) Set the url

        spec.pathParams("employeeName","employee","id",8);


        //2) Set the expected data

        //3) Send the Request
        Response response = given().
                                    spec(spec).
                                    when().
                                    get("/{employeeName}/{id}");

        response.prettyPrint();


        //4)Assertion
        response.then().statusCode(200).contentType(ContentType.JSON).
                header("Connection","keep-alive").body("status", equalTo("success"),
                "data.employee_name",equalTo("Rhona Davidson"),
                "data.employee_salary", equalTo(327900),
                "data.employee_age", equalTo(55),
                "data.profile_image", equalTo(""),
                "message", equalTo("Successfully! Record has been fetched."));


    }

    @Test
    public void get02(){
        //1) Set the url

        spec.pathParams("employeeName","employee","id",8);


        //2) Set the expected data

        //3) Send the Request
        Response response = given().
                spec(spec).
                when().
                get("/{employeeName}/{id}");

        response.prettyPrint();



        //4)Assertion
        response.then().statusCode(200).contentType(ContentType.JSON).
                header("Connection","keep-alive");

        //Use hard assertion +JsonPath
        JsonPath json =response.jsonPath();//we created json object,so i able to access all navigate methods by json object

        assertEquals("status does not match","success",json.getString("status"));
        assertEquals("id does not match",8,json.getInt("data.id"));
        assertEquals("name does not match","Rhona Davidson",json.getString("data.employee_name"));
        assertEquals("salary does not match","327900",json.getString("data.employee_salary"));
        assertEquals("age does not match","55",json.getString("data.employee_age"));
        assertEquals("image does not match","",json.getString("data.profile_image"));
        assertEquals("message does not match","Successfully! Record has been fetched.",json.getString("message"));

        //Use soft assertion +JsonPath
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(json.getString("status"),"success","status does not match");
        softAssert.assertEquals(json.getString("data.id"), "8"," id does not match");
        softAssert.assertEquals(json.getString("data.employee_name"),"Rhona Davidson","name does not match");
        softAssert.assertEquals(json.getString("data.employee_salary"),"327900","salary does not match");
        softAssert.assertEquals(json.getString("data.employee_age"),"55","age does not match");
        softAssert.assertEquals(json.getString("data.profile_image"),"","image does not match");
        softAssert.assertEquals(json.getString("message"),"Successfully! Record has been fetched.","message does not match");

        softAssert.assertAll();



    }







 }
