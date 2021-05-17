package get_method;

import base_urls.OpenWeatherBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.OpenWeatherMapTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest10 extends OpenWeatherBaseUrl {

    /*
    When
	 		I send GET Request to the Url https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0
	 	Then
	 		Status code is 200
	 		And Response body is like {
                                        "coord": {
                                            "lon": -0.1257,
                                            "lat": 51.5085
                                                 },
                                        "weather": [
                                                 {
                                                "id": 500,
                                                "main": "Rain",
                                                "description": "light rain",
                                                "icon": "10d"
                                                  }
                                               ],
                                        "base": "stations",
                                        "main": {
                                            "temp": 286.32,
                                            "feels_like": 285.31,
                                            "temp_min": 284.82,
                                            "temp_max": 287.59,
                                            "pressure": 998,
                                            "humidity": 62
                                        },
                                        "visibility": 10000,
                                        "wind": {
                                            "speed": 5.14,
                                            "deg": 230
                                        },
                                        "rain": {
                                            "1h": 0.15
                                        },
                                        "clouds": {
                                            "all": 75
                                        },
                                        "dt": 1621172702,
                                        "sys": {
                                            "type": 1,
                                            "id": 1414,
                                            "country": "GB",
                                            "sunrise": 1621138025,
                                            "sunset": 1621194411
                                        },
                                        "timezone": 3600,
                                        "id": 2643743,
                                        "name": "London",
                                        "cod": 200

     */

    @Test
    public  void get01(){
        //1)Set the url
        spec.pathParams("first","data","second",2.5,"third","weather").
                queryParams("q","London","appid","f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");

        //2)Set the expected data
        OpenWeatherMapTestData expectedData = new OpenWeatherMapTestData();

        //3)Send the request
        Response response = given().
                                    spec(spec).
                                    when().
                                    get("/{first}/{second}/{third}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();// to reach actual data ,for example for second assertion


        //4)Assert the outputs
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.coordSetup().get("lat"), (Float)json.getFloat("coord.lat")); //(Float) type casting,getFloat() returns primitive float ,not Float.in my method it is Wrapper class Float
        assertEquals(expectedData.weatherSetUp().get("main"),json.getString("weather[0].main"));//WhY weather[0]? Because it is an array,array uses indexes,and the first index is 0
        assertEquals(expectedData.expectedDataSetUp().get("base"),json.getString("base"));  // Be careful for ==>expectedDataSetUp(), and base<=>base
        assertEquals(expectedData.mainSetUp().get("humidity"),(Float) json.getFloat("main.humidity"));// when it fails change humidity value in MapTestData Class
        assertEquals(expectedData.expectedDataSetUp().get("visibility"), json.getInt("visibility"));









    }




}
