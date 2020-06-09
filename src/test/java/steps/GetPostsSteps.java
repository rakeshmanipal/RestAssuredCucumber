package steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.messages.Messages;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;
import pojo.Posts;
import utilities.APIConstant;
import utilities.RestAssuredExtention;
import utilities.RestAssuredExtentionV2;


import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetPostsSteps {

    private static ResponseOptions<Response> response;
    private static String token;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGetOperationFor(String url) throws Throwable {
        response = RestAssuredExtention.GetOpsWithToken(url,response.getBody().jsonPath().get("access_token"));
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorName(String authorName) throws Throwable {
        var posts=response.getBody().as(Posts.class);
        assertThat(posts.getAuthor(),equalTo(authorName));

        //MatcherAssert.assertThat(response.getBody().jsonPath().get("author"), CoreMatchers.hasItem("Karthik KK"));

    }


    @Then("I should see the author names")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethod.performContainsCollection();
    }

    @Then("I should see Verify GET parameter")
    public void iShouldSeeVerifyGETParameter() {
        BDDStyleMethod.performPathParameter();
    }

    @Then("I should see Verify Query GET parameter")
    public void iShouldSeeVerifyQueryGETParameter() {
        BDDStyleMethod.performQueryParameter();

    }

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        BDDStyleMethod.performPostWithBodyParameter();
    }

    @Given("^I perform Authentication operation for \"([^\"]*)\" with body$")
    public void i_perform_authentication_operation_for_something_with_body(String uri,DataTable table) throws Throwable {
       var data=table.cells();

       // Map<String, String> body = new HashMap<>();
       // body.put("email",data.get(1).get(0));
       // body.put("password",data.get(1).get(1));

        LoginBody loginBody=new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        RestAssuredExtentionV2 restAssuredExtentionV2 =new RestAssuredExtentionV2(uri, APIConstant.POST,null);
        token= restAssuredExtentionV2.Authonticate(loginBody);



        //response=RestAssuredExtention.PostOpsWithBody(url,body);

    }
    @And("^I perform GET operation with path parameter for address \"([^\"]*)\"$")
    public void i_perform_get_operation_with_path_parameter_for_address_something(String uri,DataTable table) throws Throwable {
        var data=table.cells();

        Map<String, String> queryParams=new HashMap<>();
        queryParams.put("id",data.get(1).get(0));

        //Response
        //RestAssuredExtention.GetWithQueryParamWithToken(url,queryParams,response.getBody().jsonPath().get("access_token"));
        RestAssuredExtentionV2 restAssuredExtentionV2=new RestAssuredExtentionV2(uri,APIConstant.GET,token);
       response= restAssuredExtentionV2.ExecuteAPIWithQueryParams(queryParams);


    }

    @Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
    public void i_should_see_the_street_name_as_something_for_the_something_address(String streetName, String type) throws Throwable {
        var location=response.getBody().as(Location[].class);

        //Filter address based on the type of the address
        Address address=location[0].getAddress().stream().filter(x->x.getType().equalsIgnoreCase(type))
                                                         .findFirst().orElse(null);
        assertThat(address.getStreet(),equalTo(streetName));

    }

    @Then("I should see the author name as {string} with json validation")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) {
        //return body as string
        var responseBody=response.getBody().asString();

        //assert
        assertThat(responseBody,matchesJsonSchemaInClasspath("post.json"));


    }
}
