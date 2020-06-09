package steps;

import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BDDStyleMethod {

    public static void simplePost(String postNumber){
        given().contentType(ContentType.JSON).when().get(String.format("http://localhost:3000/posts/%s",postNumber)).
                then().body("author", Is.is("Karthik KK"));
    }

    public static void performContainsCollection(){
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/posts/")
         .then()
                .body("author", Matchers.containsInAnyOrder("Karthik KK","Karthik KK",null)).statusCode(200);

    }

    public static void performPathParameter(){
        given()
                .contentType(ContentType.JSON)
        .with()
                .pathParam("post",1)
        .when()
                .get("http://localhost:3000/posts/{post}")
        .then()
               .body("author", CoreMatchers.containsString("Karthik KK"));
    }

    public static void performQueryParameter(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("id",1).
        when()
                .get("http://localhost:3000/posts/").
        then()
                .body("author", CoreMatchers.hasItem("Karthik KK"));
    }

    public static void performPostWithBodyParameter(){
        HashMap<String,String> postContent=new HashMap<>();
        postContent.put("id","3");
        postContent.put("title","Rest-Assured Course");
        postContent.put("author","ExecuteAutomation");
        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts/").
        then()
                .body("author",Is.is("ExecuteAutomation"));

    }


}
