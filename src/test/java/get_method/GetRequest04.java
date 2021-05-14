package get_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static io.restassured.RestAssured.*;



public class GetRequest04 extends JsonPlaceHolderBaseUrl {

    /*
        When
	 	I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos
	    And Accept type is “application/json”
	    Then
	    HTTP Status Code should be 200
	    And Response format should be "application/json"
	    And there should be 200 todos
	    And "quis eius est sint explicabo" should be one of the todos
	    And 2, 7, and 9 should be among the userIds
     */

     @Test
     public void get01(){
         //1)Set the url
         spec.pathParam("first","todos");

         //2)Set the expected Data
         //3)Send the request
         Response response = given().spec(spec).accept("application/json").when().get("/{first}");
         response.prettyPrint();

         //4)Assert the outout
         response.
                 then().
                 assertThat().
                 statusCode(200).
                 contentType(ContentType.JSON).//intead of “application/JSON”
                 body("id", hasSize(200)).
                 body("title",hasItem("quis ut nam facilis et officia qui")).
                 body("userId",hasItems(2,7,9));






  }
}