package get_method;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest05 extends HerokuappBaseUrl {
   /*
         When
	  		I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
	  	 Then
	  		Among the data there should be someone whose first name is “Mark” and last name is “Ericsson”
    */

    @Test
    public void get01(){
        //1)Set the url
        spec.pathParam("first","booking").queryParams("firstname","Mary",
                "lastname","Jackson");//on postman by using "?" query parameters,in automation by queryParams()

        //2) Set the expected data

        //3) Send the Request
        Response response = given()
                                   .spec(spec)
                                   .when()
                                   .get("/{first}");
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200);

        assertTrue(response.asString().contains("bookingid"));//If Mary Jackson exists, i can get bookingid=8

        //git config --global user.email "huseyinmkocal@gmail.com"

        //git config --global user.name "huseyinmusab"

    }









}
