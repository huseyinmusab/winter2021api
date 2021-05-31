package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TechProEdUrl {

    //Create RequestSpecification object
    protected RequestSpecification spec;
    //If you use @Before annotation before a method, it means the method will be executed before every test method
    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://api-techproed-test.herokuapp.com/courses").build();

    }

}
