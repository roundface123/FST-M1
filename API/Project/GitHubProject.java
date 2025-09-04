package project;

import java.io.IOException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GitHubProject {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	String sshkey = "ssh-";
	int id;
	
	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().
						addHeader("Content-Type", "application/json").
						addHeader("Authorization", "token ").
						setBaseUri("https://api.github.com").
						build();
		
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType("application/json").				
				build();
	}

	@Test(priority = 1)
	public void addSshKey() {
		String reqBody = "{"
				+ "\"title\": \"TestAPIKey\","
				+ "\"key\": \"" + sshkey + "\""
				+"}";
		
		Response response = RestAssured.given().spec(requestSpec) 
			.body(reqBody)
			.log().all()
		.when().post("/user/keys");
		
		id = response.then().extract().path("id");
		System.out.println(id);
		response.then().log().all();
		response.then().statusCode(201);
	
	}
	
	@Test(priority = 2)
	public void getSshKey() {
		RestAssured.given().spec(requestSpec) 
				.pathParam("keyId", id) 
				.log().all() 
			.when()
				.get("/user/keys/{keyId}") 
			.then().spec(responseSpec) 
			    .body("id", Matchers.equalTo(id)) 
			    .body("title", Matchers.equalTo("TestAPIKey"))
			    .log().all(); 
	}
	
	@Test(priority = 3)
	public void deleteSshKey()  throws IOException {
		RestAssured.given().spec(requestSpec) 
			.pathParam("keyId", id) 
			.log().all()
		.when()
			.delete("/user/keys/{keyId}") 
		.then()
			.statusCode(204)
			.log().all();
	}
}
