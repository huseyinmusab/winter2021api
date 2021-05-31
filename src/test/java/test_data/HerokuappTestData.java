package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuappTestData {

    Map<String,Object> bookingDatesMap = new HashMap<>();
    Map<String,Object> bookingDetailsMap = new HashMap<>();

    public Map<String,Object>setUpData(){
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-01-01");

        bookingDetailsMap.put("firstname","Mark");
        bookingDetailsMap.put("lastname","Wilson");
        bookingDetailsMap.put("totalprice",720);
        bookingDetailsMap.put("depositpaid",false);
        bookingDetailsMap.put("bookingdates",bookingDatesMap);
        bookingDetailsMap.put("additionalneeds","Breakfast");

        return bookingDetailsMap;
    }



}
