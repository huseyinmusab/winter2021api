package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestDataForPostAndPut {


    //For ObjectMappers test datas
    public String expected =  " {\n" +
            "                                       \n" +
            "                              \"userId\": 10,\n" +
            "                                        \"id\": 198,\n" +
            "                                        \"title\": \"quis eius est sint explicabo\",\n" +
            "                                        \"completed\": true         }";


    public Map<String, Object> expectedDataSetUp() {

        Map<String, Object> expected = new HashMap<>();
        expected.put("userId", 55);
        expected.put("title", "Tidy your room");
        expected.put("completed", false);
        return expected;

    }

    public Map<String, Object> expectedPatchDataSetUp() {

        Map<String, Object> expected = new HashMap<>();
        expected.put("title", "Tidy your room");
        return expected;

    }
}