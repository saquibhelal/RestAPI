package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

public class RestClient {

	//1. GET Method without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient= HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);//http get req
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpget);//hit the GET URL
		return closeableHttpResponse;
		
	}
	
	//2. GET Method With Header
		public CloseableHttpResponse get(String url,HashMap<String, String>headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient= HttpClients.createDefault();
			HttpGet httpget=new HttpGet(url);//http get req
			
			for(Map.Entry<String, String>entry:headerMap.entrySet()){
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpget);//hit the GET URL
			return closeableHttpResponse;
			
		}
		
		//3. POST METHOD:
		public CloseableHttpResponse post(String url,String entityString,HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient= HttpClients.createDefault();
            HttpPost httpPost=new HttpPost(url);//Http Post Req....
            httpPost.setEntity(new StringEntity(entityString));// for Payload...
            
            //For Headers:
            for(Map.Entry<String, String>entry:headerMap.entrySet()){
            	httpPost.addHeader(entry.getKey(),entry.getValue());
			}
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
            return closeableHttpResponse;
		}
	
	
	
}
