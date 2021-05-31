package post_method;

import base_urls.AgroMonitoringBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import test_data.AgroMonitoringTestData;

import static io.restassured.RestAssured.given;

public class PostRequest03 extends AgroMonitoringBaseUrl {
   /*
   When
		 I send POST Request to the Url "http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0"
		 with the Request Body like  {
										   "name":"Polygon Sample",
										   "geo_json":{
										      "type":"Feature",
										      "properties":{},
										      "geometry":{
										         "type":"Polygon",
										         "coordinates":[
										            [
										               [-121.1958,37.6683],
										               [-121.1779,37.6687],
										               [-121.1773,37.6792],
										               [-121.1958,37.6792],
										               [-121.1958,37.6683]
										            ]
										         ]
										      }
										   }
										}
	Then
		Assert Status Code (201)
		And Response Body should be like {
										    "id": "5fd8c383714b523b2ce1f154",
										    "geo_json": {
										        "geometry": {
										            "coordinates": [
										                [
										                    [
										                        -121.1958,
										                        37.6683
										                    ],
										                    [
										                        -121.1779,
										                        37.6687
										                    ],
										                    [
										                        -121.1773,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6683
										                    ]
										                ]
										            ],
										            "type": "Polygon"
										        },
										        "type": "Feature",
										        "properties": {

										        }
										    },
										    "name": "Polygon Sample",
										    "center": [
										        -121.1867,
										        37.67385
										    ],
										    "area": 190.9484,
										    "user_id": "5fd8c02a3da20c000759e0f8",
										    "created_at": 1608041347
										}
    */

    /*
    my postman:
    {
    "id": "60a29debb5d889129e02fdfc",
    "geo_json": {
        "type": "Feature",
        "properties": {},
        "geometry": {
            "type": "Polygon",
            "coordinates": [
                [
                    [
                        -121.1958,
                        37.6683
                    ],
                    [
                        -121.1779,
                        37.6687
                    ],
                    [
                        -121.1773,
                        37.6792
                    ],
                    [
                        -121.1958,
                        37.6792
                    ],
                    [
                        -121.1958,
                        37.6683
                    ]
                ]
            ]
        }
    },
    "name": "Polygon Sample",
    "center": [
        -121.1867,
        37.67385
    ],
    "area": 190.9484,
    "user_id": "5fd8c02a3da20c000759e0f8",
    "created_at": 1621269995
}

     */

    @Test
    public void post01(){
        //1)Set the url
        spec.pathParams("first","agro",
                "second",1.0
                ,"third","polygons").
                queryParam("appid","f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");

        //2)Set the expected Data
        AgroMonitoringTestData expectedData = new AgroMonitoringTestData();


        //3)Send the request
        Response response = given().
                                   spec(spec).
                                   contentType(ContentType.JSON).
                                   body(expectedData.expectedDataSetUp()).
                                   when().
                                   post("/{first}/{second}/{third}");

        response.prettyPrint();



        //4)Assertion
        response.
                then().
                assertThat().
                statusCode(201).
                body("geo_json.geometry.type", equalTo("Polygon"),
                        "geo_json.geometry.coordinates[0][0][0]",equalTo(-121.1958f),
                        "geo_json.geometry.coordinates[0][2][1]",equalTo(37.6792f),
                        "name",equalTo("Polygon Sample"),
                        "center[1]",equalTo(37.67385f),
                        "user_id",equalTo("5fd8c02a3da20c000759e0f8"));

    }





}
