package post_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JsonPlaceHolderTestDataForPost;

import java.net.PortUnreachableException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends JsonPlaceHolderBaseUrl {
   /*
      When
	  		I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
	  		with the request body {
								    "userId": 55,
								    "title": "Tidy your room",
								    "completed": false
								   }
		Then
			Status code is 201
			And response body is like {
									    "userId": 55,
									    "title": "Tidy your room",
									    "completed": false,
									    "id": 201

    */
    @Test
    public void post01(){
        //1)Set the url
        spec.pathParam("first","todos");

        //2)Set the expected data
        JsonPlaceHolderTestDataForPost expectedData = new JsonPlaceHolderTestDataForPost();

        //3)Send the request
        //contentType(ContentType.JSON)==> if it is working without this,do not need to use,
        // if it needs use it,How i understand to use=>when it doesnt give me all datas, use contentType(ContentType.JSON)
        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                body(expectedData.expectedDataSetUp()).
                when().
                post("/{first}");

        response.prettyPrint();

        //4)Assertion
        //1.Way By using GSON
        Map<String,Object>actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(201, response.statusCode()); // i can only reach to statusCode by using response Object,Not GSON
        assertEquals(expectedData.expectedDataSetUp().get("completed"), actualData.get("completed"));
        assertEquals(expectedData.expectedDataSetUp().get("title"), actualData.get("title"));
        assertEquals(expectedData.expectedDataSetUp().get("userId"), actualData.get("userId"));
        //assertEquals(expectedData.expectedDataSetUp().get("id"), actualData.get("id"));




        //2.Way By using JsonPath with Soft Assertion=(Verification)
        //a)Create soft assert Object  b) Use user methods   c)assertAll()

        JsonPath json = response.jsonPath();   //Everything is about response is stored in json Object,by using this object i can access to completed,title,userId,
        SoftAssert softAssert = new SoftAssert(); //without telling assertAll() this softAssert is useless

        softAssert.assertEquals(json.getBoolean("completed"), expectedData.expectedDataSetUp().get("completed"));
        softAssert.assertEquals(json.getString("title"), expectedData.expectedDataSetUp().get("title"));
        softAssert.assertEquals(json.getInt("userId"), expectedData.expectedDataSetUp().get("userId"));

        //softAssert.assertEquals(json.getString("userId"), expectedData.expectedDataSetUp().get("userId"));
        //java.lang.AssertionError: The following asserts failed:
        //	expected [55] but found [55]
        //When i make mistake about data type i can get this kind of error==>Instead of getString() ,getInt() i should use
        softAssert.assertAll();



        //3.Way By using body()
        response.
                then().
                assertThat().
                statusCode(201).body("completed",equalTo(false),
                "title",equalTo("Tidy your room"),
                "id",equalTo(201));


        //For Matchers()===> change it to it to delete the Matchers in your code===>import static org.hamcrest.Matchers.*;
        //statusCode(201);after statusCode(201) run the code to check ,after you are sure it passes ,them you can add body() to response chain















    }


    }
