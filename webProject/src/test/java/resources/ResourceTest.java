package resources;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class ResourceTest {
	
	@Test public void
	lotto_resource_returns_200_with_expected_id_and_winners() {
		RestAssured.baseURI = "https://localhost";
		RestAssured.port = 8443;
		RestAssured.basePath = "/bankProjectWeb";
		RestAssured.authentication = basic("lu", "lu");
		//RestAssured.config = RestAssured.config().sslConfig(new SSLConfig("/truststore_javanet.jks", "test1234");
	    
	    when().
	            get("/rs/account/{id}", 4).
	    then().
	            statusCode(200).
	            body("id", equalTo(4));

	}
}
