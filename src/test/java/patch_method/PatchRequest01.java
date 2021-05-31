package patch_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import test_data.JsonPlaceHolderTestDataForPostAndPut;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {

    /*
      When
	 		I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "title": "Tidy your room",
										   }
	   Then
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 10,
									    "title": "Tidy your room",
									    "completed": true,
									    "id": 198
									  }
     */

    @Test
    public void patch01(){

        //1)Set the url
        spec.pathParams("first","todos",
                "second",198);

        //2)Set the expected data
        JsonPlaceHolderTestDataForPostAndPut expected =new JsonPlaceHolderTestDataForPostAndPut();

        //3)Send the request
        Response response = given().
                                    spec(spec).
                                    contentType(ContentType.JSON).
                                    body(expected.expectedPatchDataSetUp()).
                                    when().
                                    patch("/{first}/{second}");

        response.prettyPrint();
        //FOR PATCH I DO NOT NEED TO SEND ALL DATA LIKE PUT

        //4)Assertion

       // response.
       //         then().
       //         assertThat().
       //         statusCode(200).
       //         body("title", Matchers.equalTo("Tidy your room"),
       //         "userId",Matchers.equalTo(10),
       //         "completed",Matchers.equalTo(true));

       //Instead of using spcific values ("Tidy your room"),we should use it the second way.WE MAKE IT UNTOUCHABLE,So we just change the data in
       //JsonPlaceHolderTestDataForPostAndPut class.
       //For example today we use  "Tidy your room",next week our managewr can say it to change "Walk around ",so
       //we just need to change the data in JsonPlaceHolderTestDataForPostAndPut class,we do not touch our Automation Test
       //HERE:

        response.
                then().
                assertThat().
                statusCode(200).
                body("title", Matchers.equalTo(expected.expectedDataSetUp().get("title")),
                        "userId",Matchers.equalTo(expected.expectedPatchDataSetUp().get("userId")),
                        "completed",Matchers.equalTo(expected.expectedPatchDataSetUp().get("completed")));

    }

}
