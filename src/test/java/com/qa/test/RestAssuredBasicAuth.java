package com.qa.test;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBasicAuth {

	@Test
	public void basicAuthTest() {
		
		RestAssured.baseURI="http://httpbin.org";
		RequestSpecification request = RestAssured.given();
		request.auth().basic("user", "passwd");
		Response response = request.get("/basic-auth/user/passwd");
		response.prettyPrint();

	}

}
