package delete_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DeleteRequest01 extends JsonPlaceHolderBaseUrl {
/*
    When
    I send DELETE Request to the Url https://jsonplaceholder.typicode.com/todos/19
    Then
    Status code is 200
    And Response body is {}

 */

    @Test
    public void delete01(){
        //1)Set the url
        spec.pathParams("todosName","todos",
                "id",19 );

        //2)Set the expected Data
        Map<String,Object>expected = new HashMap<>();

        //3)Send the delete request
        Response response = given().spec(spec).when().delete("/{todosName}/{id}");
        //content type is used mostly for body()

        response.prettyPrint();

        //4)Assertion
        //Use GSON for De-Serialization (I am trying to convert the json data to map(JAVA OBJECT),because my expected data is in a Map.
        // Actual data and expected data must be java object to COMPARE )
        Map<String,Object>actual= response.as(HashMap.class);


        //THIS DOESNT LET ME TO USE
        //response.then().assertThat().statusCode(200).body(actual.get(""), Matchers.equalTo(expected.get("")));
        response.then().assertThat().statusCode(200);
        assertEquals(expected.get(""),actual.get(""));
        assertEquals(expected,actual);
        assertEquals(expected.size(),actual.size());
        //different ways of asserting empty maps i used




    }



}
