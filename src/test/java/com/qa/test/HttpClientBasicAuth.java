package com.qa.test;

import java.io.IOException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class HttpClientBasicAuth {

	@Test
	public void handleBasicAuthHttp() throws ClientProtocolException, IOException {

		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(new AuthScope("httpbin.org", 80), new UsernamePasswordCredentials("user", "passwd"));
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(creds).build();
		HttpGet get = new HttpGet("http://httpbin.org/basic-auth/user/passwd");
		CloseableHttpResponse response = client.execute(get);
		System.out.println(EntityUtils.toString(response.getEntity()));

	}

}
