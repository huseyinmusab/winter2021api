package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringBaseUrl {
    //Create RequestSpecification object
    protected RequestSpecification spec; //spec object name ,you can name it whatever you want

    //If you use @Before annotation before a method, it means the method will be executed before every test method
    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();
    }
}
