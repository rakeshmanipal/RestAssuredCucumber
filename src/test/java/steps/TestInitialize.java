package steps;

import io.cucumber.java.Before;
import utilities.RestAssuredExtention;

public class TestInitialize {

    @Before
    public void TestSetUp(){
        RestAssuredExtention restAssuredExtention=new RestAssuredExtention();
    }

}
