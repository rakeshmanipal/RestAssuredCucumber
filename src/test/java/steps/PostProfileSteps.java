package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtention;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostProfileSteps {
    private static ResponseOptions<Response> response;

    @Given("I Perform POST operation for {string} with body")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table)  {
        var data=table.cells();

        //Set body
        HashMap<String, String> body=new HashMap<>();
        body.put("name",data.get(1).get(0));

        //Path Parameter
        HashMap<String,String> pathParams=new HashMap<>();
        pathParams.put("profileNo",data.get(1).get(1));

        //Perform Post Operation
        response = RestAssuredExtention.PostOpsWithBodyAndPathParams(url,pathParams,body);

    }

    @Then("i should see the body has the name as {string}")
    public void iShouldSeeTheBodyHasTheNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"),equalTo(name));
    }


    @Given("I ensure to Perform POST operation for {string} with body as")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(String url, DataTable table) {
        var data=table.cells();
        //Map for Body
        Map<String, String> body=new HashMap<>();
        body.put("id",data.get(1).get(0));
        body.put("title",data.get(1).get(1));
        body.put("author",data.get(1).get(2));

        //Perform Post Operation
        RestAssuredExtention.PostOpsWithBody(url,body);
    }

    @And("I Perform Delete Operation for {string}")
    public void iPerformDeleteOperationFor(String url, DataTable table) {
        var data=table.cells();

        Map<String, String> pathParams=new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));

        //Perform Delete Operation
        RestAssuredExtention.DeleteOpsWithPathParams(url,pathParams);
    }

    @And("I perform GET operation with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url,DataTable table) {
        var data=table.cells();

        Map<String, String> pathParams=new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));
        response=RestAssuredExtention.GetWithPathParam(url,pathParams);



    }

    @Then("I should not see the body with title as {string}")
    public void iShouldNotSeeTheBodyWithTitleAs(String title) {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));

    }

    @And("I Perform PUT Operation for {string}")
    public void iPerformPUTOperationFor(String url, DataTable table) {
        var data=table.cells();
        //Map for Body
        Map<String, String> body=new HashMap<>();
        body.put("id",data.get(1).get(0));
        body.put("title",data.get(1).get(1));
        body.put("author",data.get(1).get(2));

        //path param
        Map<String, String> pathParams=new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));

        //Perform Put Operation
        RestAssuredExtention.PutOpsWithBodyAndPathParam(url,body,pathParams);

    }

    @Then("I should see the body with title as {string}")
    public void iShouldSeeTheBodyWithTitleAs(String title) {
        assertThat(response.getBody().jsonPath().get("title"), is(title));
    }
}
