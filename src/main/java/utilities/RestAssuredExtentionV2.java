package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import javax.swing.*;
import java.util.Map;

public class RestAssuredExtentionV2 {

    private RequestSpecBuilder builder=new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     *RestAssuredExtentionV2 Constroctor to pass the initial
     * @param uri
     * @param method
     * @param token
     */

    public  RestAssuredExtentionV2(String uri,String method, String token){
        //Formulate the API URL
        this.url="http://localhost:3000"+uri;
        this.method=method;

        if(token!=null){
            builder.addHeader("Authorization", "Bearer " + token);
        }
    }

    /**
     * ExecuteAPI to execute the API Get/Post/Delete
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> ExecuteAPI(){
        RequestSpecification requestSpecification=builder.build();
        RequestSpecification request= RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if(this.method.equalsIgnoreCase(APIConstant.POST))
            return request.post(this.url);
        else if(this.method.equalsIgnoreCase(APIConstant.DELETE))
            return request.delete(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.GET))
            return request.get(this.url);
        return null;

    }

    /**
     * Authonticate to get the token variable
     * @param body
     * @return String token
     */
    public String Authonticate(Object body){
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");

    }

    /**
     * ExecuteAPIWithQueryParams - Execute API with Query Params
     * @param queryParams
     * @return Response
     */
    public ResponseOptions<Response> ExecuteAPIWithQueryParams(Map<String, String> queryParams) {
        builder.addQueryParams(queryParams);
        return ExecuteAPI();

    }

    public ResponseOptions<Response> ExecuteAPIWithPathParams(Map<String, String> pathParam) {
        builder.addPathParams(pathParam);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteAPIWithPathParamsAndBody(Map<String, String> pathParam,Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathParam);
        return ExecuteAPI();
    }

}
