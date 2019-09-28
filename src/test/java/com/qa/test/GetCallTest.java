package com.qa.test;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetCallTest extends TestBase {

	String baseUrl, serviceUrl, uri;

	RestClient restClient;
	TestUtil testUtil;

	public GetCallTest() {

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
	public void getWithoutHeadersTest() {

		CloseableHttpResponse closeableHttpResponse = restClient.get(uri);

		/* VALIDATE STATUS CODE */
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);

		/* PRINT THE JSON RESPONSE */

		try {
			
			String jString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			
			/*ENTIRE RESPONSE */
			System.out.println(jString);
			
			JSONObject jsonObject = new JSONObject(jString);
			System.out.println("JSON RESPONSE " +jsonObject);
			
			Header[] headerArr = closeableHttpResponse.getAllHeaders();
			for(Header header : headerArr) {
				
				System.out.println(header.getName() + " - " +header.getValue());
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getWithHeadersTest() {
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		CloseableHttpResponse closeableHttpResponse = restClient.getWithHeader(uri, headerMap);
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Header[] header = closeableHttpResponse.getAllHeaders();
		for(Header hdr : header) {
			
			System.out.println(hdr.getName() +" - " +hdr.getValue());
		}
		
		try {
			String str = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			JSONObject jObj = new JSONObject(str);
			System.out.println("*************");
			System.out.println(jObj);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void validateJson() throws ParseException, IOException {
		testUtil = new TestUtil();
		
		CloseableHttpResponse closeableHttpResponse = restClient.get(uri);
		String jString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
				
		JSONObject jsonObject = new JSONObject(jString);
		testUtil.readJson(jsonObject);

	}

}
