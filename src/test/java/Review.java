public class Review {

     /*
    Which ways do you know for assertion?
    a)body(path, Matchers)



    b)JsonPath=>For navigating in json data. JsonPath class has many methods like getString, getInt..etc
     (easier) by jsonpath and json object (if the json data is complicated)
     JsonPath json= response.jsonPath();
     ==>assertEquals(expectedData.bookingDatesSetUp().get("checkout"), json.getString("booking.bookingdates.checkout"));

     **We can create JsonPath object in different ways.
       1) JsonPath json = response.jsonPath( );
       2) JsonPath json = new JsonPath(response.asString());
       3) JsonPath json = new JsonPath(FilePath.json);








    c)GSON=>For converting Json to Java object (De-Serialization) (response.as(JavaObj.class))
     Gson way (If the json data is not so complicated, you can use)
     Map<Object,Object>actualData =response.as(HashMap.class);//USAGE OF GSON==>json is converting to a map,java
     System.out.println(actualData); // to see the actual data on the console
     ==> assertEquals(expectedData.expectedDataSetUp().get("firstname"), ((Map)actualData.get("booking")).get("firstname")); //json path or gson ways we can use,this one gson
         assertEquals(expectedData.expectedDataSetUp().get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
     *** De-Serialization=> Converting Json data to Java object







    d)Object Mapper=>For converting Json to Java object (De-Serialization) (use reusable methods from utilities)

    e)POJO =>Create Pojo classes to use getters and setters

    Hard Assertion=>it stops execution when it finds the error
                   assertThat(), assertEquals(), assertTrue(), assertFalse()

    Soft Assertion=>it doesn't stop execution when it finds the error. It fives separate reports for each error

    Base url
    Path parameters
    Query parameter

    Status codes

    @JsonIgnoreProperties(ignoreUnknown = true)=>>for ignoring unknown data write this at the top of pojo class
    */



}
