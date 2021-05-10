package get_method;

import base_urls.JsonPlaceHolderaBaseUrl;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;



public class GetRequest03 extends JsonPlaceHolderaBaseUrl {
  /*
             When
		 	I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos/23
		    And Accept type is “application/JSON”
		 Then
		    HTTP Status Code should be 200
		    And Response format should be “application/JSON”
		    And “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		    And “completed” is false
		    And “userId” is 2
   */
    @Test
    public void get01(){

        //1)Set the URL
        spec.pathParams("first", "todos",
                "second",23);
        //2) Set the expected data

        //3) Send the request
        Response response = given().spec(spec.accept("application/JSON")).when().get("/{first}/{second}");
        response.prettyPrint();
        System.out.println("Status Code: " +response.getStatusCode());

        //4) Assert the output
        /*
            When a test fails, if Java does not execute the next steps it is called "Hard Assertion"(Assertion)
            But there is another assertion which is "Soft Assertion"(Verification), it executes all tests and gives you report about
            the passed ones and failed ones
         */
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));
        /*
            Note: If you use body() for every step it uses hard assertion.
                  If you use just a single body() with multiple test steps, it gives you report for every failed test
         */
        //2.way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));

        //3.way
        //HTTP Status Code should be 200
        //assertEquals(expected,actual)
        assertEquals(200,response.getStatusCode());
        assertEquals("Status Code must be 200 but it is not",200,response.getStatusCode());

        //Response format should be “application/JSON”
        assertTrue(response.getContentType().contains("application/json"));//it is a small detail,check it on console and copy paste
        assertTrue("Content Type is not in json format",response.getContentType().contains("application/json"));

        //“title” is “et itaque necessitatibus maxime molestiae qui quas velit”
        //assertEquals("et itaque necessitatibus maxime molestiae qui quas velit",response.asString().contains(""));NOT WORK
        assertTrue(response.asString().contains("et itaque necessitatibus maxime molestiae qui quas velit"));

        //“completed” is false
        assertTrue(response.asString().contains("false"));

        //“userId” is 2
        assertTrue(response.asString().contains("\"userId\": 2"));




    }
}
