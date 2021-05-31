package utilities;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    //Create 2 methods for De-Serialization and Serialization by using Object mapper
    //We will create 2 methods and we will use them again again

    //1.Step : Create an Object from object mapper class

  private static ObjectMapper mapper;
  static {
      mapper = new ObjectMapper();
  }




  //2.Step:   Create method for DE-SERIALIZATION
    //Since we need flexibilty in return type (because in java there are many types of Object, we used generic method)
    public static <T> T convertJsonToJava(String json,Class<T> cls){

      T javaResult = null;
        try {
            javaResult = mapper.readValue(json,cls);//Use readVlaue()from ObjectMapper for De-Serialization
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java"+ e.getMessage());
        }
        return javaResult;

        //WHY WE DO WE PREFER TO USE  TRY-CATCH BLOCK INSTEAD OF THROW IN REUSABLE METHODS?
        //Because try-catch can run again and again for everytime and but throws runs just for once

    }



    //Create a method for SERIALIZATION (Java to Json)
    //for this method why we used String as a data type?because the data type of Json is String
    public static String convertJavaToJson(Object obj){
      String jsonResult = null;

        try {
           jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java could not be converted to Json"+ e.getMessage());
        }
        return jsonResult;


    }




}
