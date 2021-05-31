package test_data;

import java.util.HashMap;
import java.util.Map;

public class AgroMonitoringTestData {

    //we prefered to use Float instead of using List
    public float coordinates[][][]={ { {-121.1958f, 37.6683f},
            {-121.1779f,37.6687f},
            {-121.1773f,37.6792f},
            {-121.1958f,37.6792f},
            {-121.1958f,37.6683f}  } };





    public Map<String ,Object>geometrySetUp(){
        Map<String,Object>geometry = new HashMap<>();
        geometry.put("type","Polygon");
        geometry.put("coordinates",coordinates);
        return geometry;
    }

    public  Map<String,Object>properties =new HashMap<>();// i cretaed an empty map ,because it is empty==>"properties": {}



    public Map<String,Object> geoJsonSetUp(){
        Map<String,Object>geoJson= new HashMap<>();
        geoJson.put("type","Feature");
        geoJson.put("properties",properties);
        geoJson.put("geometry",geometrySetUp());

        return geoJson;
    }

    public Float center[] ={-121.1867f,37.67385f};




    public Map<String,Object>expectedDataSetUp(){
        Map<String,Object>expected=new HashMap<>();
        expected.put("id","60a29debb5d889129e02fdfc");
        expected.put("geo_json",geoJsonSetUp());
        expected.put("name","Polygon Sample");
        expected.put("center",center);
        expected.put("area",190.9484);
        expected.put("userId","5fd8c02a3da20c000759e0f8");
        //expected.put("created_at","1621269995"); we skipped this

        return expected;
    }



}
