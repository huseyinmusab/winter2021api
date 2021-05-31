package object_mapper;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.xml.dom.Tag;
import test_data.JsonPlaceHolderTestData;
import test_data.JsonPlaceHolderTestDataForPostAndPut;
import utilities.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ObjectMapper01 extends JsonPlaceHolderBaseUrl {
    /*
        When
            I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198
        Then
            Status code is 200
            And response body is like {

                              "userId": 10,
                                        "id": 198,
                                        "title": "quis eius est sint explicabo",
                                        "completed": true         }
    */

    @Test
    public void get01(){
        spec.pathParams("todosName","todos","id",198);

        JsonPlaceHolderTestDataForPostAndPut expectedObj = new JsonPlaceHolderTestDataForPostAndPut();

        HashMap<String,Object>expectedMap = JsonUtil.convertJsonToJava(expectedObj.expected, HashMap.class);// String ==>converting to a map
        System.out.println(expectedMap);

        Response response =given().
                                  spec(spec).
                                  get("/{todosName}/{id}");
        response.prettyPrint();

        HashMap<String,Object>actualMap =  JsonUtil.convertJsonToJava(response.asString(),HashMap.class);//response body ==>converting to a map
        System.out.println(actualMap);

        response.
                then().
                assertThat().
                statusCode(200);
        assertEquals(expectedMap.get("userId"),actualMap.get("userId"));
        assertEquals(expectedMap.get("title"),actualMap.get("title"));
        assertEquals(expectedMap.get("completed"),actualMap.get("completed"));










    }
}
