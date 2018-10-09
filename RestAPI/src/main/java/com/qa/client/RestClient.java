package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//1. GET Method
	public void get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient= HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);//http get req
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpget);//hit the GET URL
		
		//a. Status Code:
		int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status COde----->"+statusCode);
		
		//b. JSON String:
		String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson= new JSONObject(responseString);
		System.out.println("Response JSN from API--->"+responseJson);
		
		//c.All Headers
		Header[] headerArray= closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> allHeaders= new HashMap<String, String>();
		
		for(Header header: headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array--->"+allHeaders);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
