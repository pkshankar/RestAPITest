package com.qa.test;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.testdata.TestData;
import com.qa.util.TestUtil;

public class PostCallTest extends TestBase {
	
	String baseUrl, serviceUrl, uri;
	RestClient restClient;
	TestUtil testUtil;
	TestData testData;

	public PostCallTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		restClient = new RestClient();
		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("serviceUrl");
		uri = baseUrl + serviceUrl;
	}
	
	@Test
	public void postCallTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		testData = new TestData("SHANKAR", "LEADER");
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("C:\\Users\\pkshank\\eclipse-workspace\\RestAPITest\\src\\main\\java\\com\\qa\\testdata\\requestdata.json"), testData);
	//	objectMapper.writeValueAsString(testData);
		CloseableHttpResponse closeableHttpResponse = restClient.post(uri, objectMapper.writeValueAsString(testData));
		System.out.println("RESPONSE CODE " +closeableHttpResponse.getStatusLine().getStatusCode());
		String str = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jObj = new JSONObject(str);
		System.out.println(jObj);
		
		
		
		
		
	}


}
