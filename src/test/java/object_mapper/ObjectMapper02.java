package object_mapper;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerrOkuAppTestDataForPost;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ObjectMapper02 extends HerokuappBaseUrl {

     /*
        When
                I send GET Request to the URL https://restful-booker.herokuapp.com/booking/2
            Then
                Status code is 200
                And response body is like {
                                            "firstname": "Mark",
                                            "lastname": "Ericsson",
                                            "totalprice": 726,
                                            "depositpaid": true,
                                            "bookingdates": {
                                                "checkin": "2015-08-07",
                                                "checkout": "2020-10-25"
                                             }
                                          }
     */

    @Test
    public void get01(){
        spec.pathParams("bookingName","booking","id",2);

        HerrOkuAppTestDataForPost expectedObj = new HerrOkuAppTestDataForPost();
        HashMap<String,Object>expectedMap= JsonUtil.convertJsonToJava(expectedObj.expected, HashMap.class);
        System.out.println(expectedMap);

        Response response= given().
                                  spec(spec).
                                  when().
                                  get("/{bookingName}/{id}");
        response.prettyPrint();

        HashMap<String ,Object>actualMap= JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        response.
                then().
                assertThat().
                statusCode(200);
        //assertEquals(expectedMap.get("firstname"),actualMap.get("firstname"));
        //assertEquals(expectedMap.get("lastname"),actualMap.get("lastname"));
        //assertEquals(expectedMap.get("totalprice"),actualMap.get("totalprice"));
        //assertEquals(expectedMap.get("depositpaid"),actualMap.get("depositpaid"));
        //assertEquals(expectedMap.get("depositpaid"),actualMap.get("depositpaid"));
        //assertEquals(expectedMap.get("bookingdates.checkin"),actualMap.get("depositpaid.checkin"));
        //assertEquals(expectedMap.get("bookingdates.checkout"),actualMap.get("depositpaid.checkout"));
        //Bu api daki datalar surekli degf=istigi icin hatalar verebilir

        //2nd way of asserting bookingdates CASTING
        assertEquals(((Map)expectedMap.get("bookingdates")).get("checkin"),((Map)actualMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedMap.get("bookingdates")).get("checkout"),((Map)actualMap.get("bookingdates")).get("checkout"));



    }


}
