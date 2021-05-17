package get_method;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest02 extends HerokuappBaseUrl {

     /*
            When
			    I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/1001
			Then
				HTTP Status code should be 404
			And
				Status Line should be HTTP/1.1 404 Not Found
			And
	            Response body contains "Not Found"
	        And
	            Response body does not contain "TechProEd"
	        And
	            Server is "Cowboy"
     */
     @Test
     public void get02(){

         //1)Set the URL
         //String url = "https://restful-booker.herokuapp.com/booking/1001"; ==> Not recommended
         spec.pathParams("first", "booking",
                 "second", 1001);

         //2)Set the expected data

         //3)Send the request
         Response response = given().
                                     spec(spec).
                                     when().
                                     get("/{first}/{second}");
         response.prettyPrint();

         //4)Assert the output
         response
                 .then()
                 .assertThat()
                 .statusCode(404)
                 .statusLine("HTTP/1.1 404 Not Found");
         //assertTrue(true)==>Pass          assertTrue(false)==>Fail

         assertTrue(response.asString().contains("Not Found"));
         //assertFalse(false)==>Pass          assertFalse(true)==>Fail

         assertFalse(response.asString().contains("TechProEd"));//contain methodu kullanmak icin response a String muamelesi yapiyoruz
         //assertEquals(firstParameter, secondParameter); If the firstParameter matches with the secondParameter
         //you will get passed otherwise you will get failed
           assertEquals(response.getHeader("Server"), "Cowboy");

         //How to see status code on the console
         System.out.println("Status code: " + response.getStatusCode());
         //How to see status line on the console
         System.out.println("Status line: " + response.getStatusLine());




     }


}
