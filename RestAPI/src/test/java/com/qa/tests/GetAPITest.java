package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {

	TestBase testBase;
	String serciveUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	
	public GetAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		testBase=new TestBase();
		 serciveUrl= prop.getProperty("URL");
		 apiUrl= prop.getProperty("serviceURL");
		
		 url= serciveUrl+apiUrl;
		 
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException{
		restClient=new RestClient();
		restClient.get(url);
	}
	
	
	
	
}
