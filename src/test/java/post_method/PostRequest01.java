package post_method;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerrOkuAppTestDataForPost;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest01 extends HerokuappBaseUrl {
    /*
    When
	 		I send POST Request to the Url https://restful-booker.herokuapp.com/booking
	 		with the request body {
								    "firstname": "Selim",
								    "lastname": "Ak",
								    "totalprice": 11111,
								    "depositpaid": true,
								    "bookingdates": {
								                     "checkin": "2020-09-09",
								                     "checkout": "2020-09-21"
								     }
								  }
	 	Then
	 		Status code is 200
	 		And response body should be like {
											     "bookingid": 11,
											     "booking": {
											          "firstname": "Selim",
											          "lastname": "Ak",
											          "totalprice": 11111,
											          "depositpaid": true,
											          "bookingdates": {
											          "checkin": "2020-09-09"
											          "checkout": "2020-09-21"

     */

    //when you do it for test data===> always start the inner part
    @Test
    public void post01(){
        //1) Set the url
        spec.pathParam("first","booking");

        //2) Set the expected data
        HerrOkuAppTestDataForPost expectedData = new HerrOkuAppTestDataForPost();

        //3)Send post request
        Response response = given()
                                   .spec(spec)
                                   .contentType(ContentType.JSON)
                                   .body(expectedData.expectedDataSetUp())
                                   .when()
                                   .post("/{first}");
        response.prettyPrint();



        Map<Object,Object>actualData =response.as(HashMap.class);//USAGE OF GSON==>json is converting to a map
        System.out.println(actualData); // to see the actual data on the console


         //4)Assertion
        //1.way Gson way (If the json data is not so complicated, you can use)
        assertEquals(expectedData.expectedDataSetUp().get("firstname"), ((Map)actualData.get("booking")).get("firstname")); //json path or gson ways we can use,this one gson
        assertEquals(expectedData.expectedDataSetUp().get("lastname"), ((Map)actualData.get("booking")).get("lastname")); //json path or gson ways we can use,this one gson
        assertEquals(expectedData.expectedDataSetUp().get("totalprice"), ((Map)actualData.get("booking")).get("totalprice")); //json path or gson ways we can use,this one gson
        assertEquals(expectedData.expectedDataSetUp().get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid")); //json path or gson ways we can use,this one gson
        assertEquals(expectedData.bookingDatesSetUp().get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin")); //json path or gson ways we can use,this one gson
        assertEquals(expectedData.bookingDatesSetUp().get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout")); //json path or gson ways we can use,this one gson

        //2.way(easier) by jsonpath and json object
        JsonPath json= response.jsonPath();

        assertEquals(expectedData.expectedDataSetUp().get("firstname"), json.getString("booking.firstname"));
        assertEquals(expectedData.expectedDataSetUp().get("lastname"), json.getString("booking.lastname"));
        assertEquals(expectedData.expectedDataSetUp().get("totalprice"), json.getInt("booking.totalprice"));
        assertEquals(expectedData.expectedDataSetUp().get("depositpaid"), json.getBoolean("booking.depositpaid"));
        assertEquals(expectedData.bookingDatesSetUp().get("checkin"), json.getString("booking.bookingdates.checkin"));
        assertEquals(expectedData.bookingDatesSetUp().get("checkout"), json.getString("booking.bookingdates.checkout"));





    }

}
