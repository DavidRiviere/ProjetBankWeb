package resources;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.internal.mapping.GsonMapper;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class ResourceTest {
	
	@BeforeClass
	public static void configConnection(){
		RestAssured.baseURI = "https://localhost";
		RestAssured.port = 8443;
		RestAssured.basePath = "/bankProjectWeb";
		RestAssured.authentication = basic("lu", "lu");
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	@Test public void getAccount() {
		
	    
	    when().
	            get("/rs/account/{id}", 4).
	    then().
	            statusCode(200).
	            body("id", equalTo(4));

	}
	
@Test public void registerOwner() {
		
	 
	}
}
