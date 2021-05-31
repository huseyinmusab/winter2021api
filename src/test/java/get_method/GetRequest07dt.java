package get_method;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest07dt extends DummyBaseUrl {

     /*
      Use JsonPath Class and Soft Assertion to do;
        When
            I send Get Request to http://dummy.restapiexample.com/api/v1/employees
        Then
            The Status code is 200
            And The name of 3rd employee is "Ashton Cox"
            And The Salary of 6th employee is 372000
            And The age of the last employee is 23
            And 21, 23, 61 are among the ages
 */
    @Test
    public void get01(){
        spec.pathParam("employeesName","employees");
        Response response=given()
                                 .spec(spec)
                                 .when()
                                 .get("/{employeesName}");

        response.prettyPrint();

        JsonPath json= response.jsonPath();

        SoftAssert softAssert= new SoftAssert();

        System.out.println(response.getContentType());

        softAssert.assertEquals(response.statusCode(),200);//why response? because i dont have any method for statuscode in json

        softAssert.assertEquals(json.getString("data[2].employee_name"), "Ashton Cox","name does not match");
        softAssert.assertEquals(json.getString("data[5].employee_salary"),"372000","salary doe not match");
        softAssert.assertEquals(json.getInt("data[-1].employee_age" ), 23,"age does not match");


        //How to assert multiple data by using JsonPath class?
        //and 21,23,61 are among the ages
        //cretae a list anf put test data in that list
        List<Integer> ageList = new ArrayList<>();
        ageList.add(21);
        ageList.add(23);
        ageList.add(61);

        //Use containAll() method
        softAssert.assertTrue(json.getList("data.employee_age").containsAll(ageList),"AT LEAST ONE OF THE AGE DOES NOT EXIST");
        softAssert.assertAll();






    }




}
