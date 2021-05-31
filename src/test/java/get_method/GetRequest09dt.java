package get_method;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest09dt extends JsonPlaceHolderBaseUrl {
 /*
        De-Serialization=> Converting Json data to Java object
        Serialization  =>Converting Java object to Json data
        1)Using GSON
        2)Using Object Mapper
     */

/*
        When
            I send GET Request to https://jsonplaceholder.typicode.com/todos/2
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 Vegur"
            And header "Server" is "cloudflare"
     */

    @Test
    public void get01(){
        //Set the url
        spec.pathParams("todosName","todos","id",2);

        //set the expected data
        JsonPlaceHolderTestData expectedDataObj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap = expectedDataObj.setUpData();

        //Send the request
        Response response = given().
                                  spec(spec).
                                  when().
                                  get("/{todosName}/{id}");

        response.prettyPrint();


        //Assertion
        Map <String, Object> actualDataMap = response.as(HashMap.class);//you are json but lets behave AS you are a map,De-Serialization
        System.out.println("Json to Java" + actualDataMap);             //Json to Java{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}

        //by turning codes to map on console we see this part==>{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}


        assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());// for statusCode we need response object

        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));
        //ASEERTION KISMINDA EXPECTED DATALARI YAZARKEN BOSLUKLARA,TYPOLARA ,KUCUK BUYUK HARFLERE DIKKKAAAAT!!!
        //COPY PASTE ILE YAZMAK DAHA IYI



        //GSON: Serialization :Converting Java Object to Json Data
        Gson gson = new Gson(); //By creating this gson object i can access the method which is in gson

        String jsonFromJavaObj = gson.toJson(actualDataMap);
        System.out.println("Java to Json" + jsonFromJavaObj);   //Java to Json{"id":2,"completed":false,"title":"quis ut nam facilis et officia qui","userId":1}



    }




}
