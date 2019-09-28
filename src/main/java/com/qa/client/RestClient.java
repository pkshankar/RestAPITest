package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class RestClient {

	/* GET CALL WITHOUT HEADERS */
	public CloseableHttpResponse get(String uri) {

		CloseableHttpResponse closeableHttpResponse = null;

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		try {

			closeableHttpResponse = closeableHttpClient.execute(httpGet);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return closeableHttpResponse;
	}

	/* GET CALL WITH HEADERS */
	public CloseableHttpResponse getWithHeader(String uri, HashMap<String, String> headerMap) {

		CloseableHttpResponse closeableHttpResponse = null;

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		for (Map.Entry<String, String> map : headerMap.entrySet()) {

			httpGet.addHeader(map.getKey(), map.getValue());
		}

		try {
			closeableHttpResponse = closeableHttpClient.execute(httpGet);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return closeableHttpResponse;
	}
	
	/* POST CALL */
	public CloseableHttpResponse post(String uri, String entityString) throws ClientProtocolException, IOException {
		
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new StringEntity(entityString));
		return closeableHttpClient.execute(httpPost);
		
		
	}

}
