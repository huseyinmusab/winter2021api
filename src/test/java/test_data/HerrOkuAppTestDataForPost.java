package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerrOkuAppTestDataForPost {

    //For ObjectMappers test data
   public  String expected=" {\n" +
           "                                            \"firstname\": \"Mark\",\n" +
           "                                            \"lastname\": \"Jones\",\n" +
           "                                            \"totalprice\":769,\n" +
           "                                            \"depositpaid\": true,\n" +
           "                                            \"bookingdates\": {\n" +
           "                                                \"checkin\": \"2018-11-17\",\n" +
           "                                                \"checkout\": \"2020-05-11\"\n" +
           "                                             }\n" +
           "                                          }";



    //for inner booking part
    public Map<String,Object>bookingDatesSetUp(){
        Map<String,Object>bookingDates = new HashMap<>();
        bookingDates.put("checkin","2021-05-16");
        bookingDates.put("checkout","2021-05-23");
        return bookingDates;

    }
    //for general
    public Map<String,Object>expectedDataSetUp(){
        Map<String,Object>expected = new HashMap<>();
        expected.put("firstname","Selim");
        expected.put("lastname", "Can");
        expected.put("totalprice", 11111);
        expected.put("depositpaid", true);
        expected.put("bookingdates", bookingDatesSetUp());
        return expected;


    }

}
