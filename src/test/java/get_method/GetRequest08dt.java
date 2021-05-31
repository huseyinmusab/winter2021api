package get_method;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest08dt extends DummyBaseUrl {

    /*
     When
         I send GET Request to URL http://dummy.restapiexample.com/api/v1/employees
     Then
         Status code is 200
         1)Print all ids greater than 10 on the console
           Assert that there are 14 ids greater than 10
         2)Print all ages less than 30 on the console
           Assert that maximum age is 23
         3)Print all employee names whose salaries are greater than 350,000
           Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
 */

    @Test
    public void get01(){
        //1)Set the url
        spec.pathParam("employees","employees");

        Response response = given().
                                    spec(spec).
                                    when().
                                    get("/{employees}");

        response.prettyPrint();


        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath json = response.jsonPath();

        //To FILTER the output we need to use "Groovy Language"
        List<Integer>idList= json.getList("data.findAll{(it.id)>10}.id"); //*********************
        //1)Print all ids greater than 10 on the console
        System.out.println(idList); //[11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
        //Assert that there are 14 ids greater than 10
        assertEquals(14,idList.size());



        //2)Print all ages less than 30 on the console
        List<Integer>listAge =json.getList("data.findAll{(it.employee_age<30)}.employee_age"); //***************
        System.out.println(listAge);  //[22, 23, 22, 19, 21, 23]
        //Assert that maximum age is 23
        //First sort and take the last element
        Collections.sort(listAge);
        assertEquals(Integer.valueOf(23),listAge.get(listAge.size()-1));



        //3)Print all employee names whose salaries are greater than 350,000
        List<String> nameList = json.getList("data.findAll{(it.employee_salary)>350000}.employee_name");
        System.out.println(nameList);
        //Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
        assertTrue("The name that you searched is not in the list",nameList.contains("Charde Marshall"));







    }



}
