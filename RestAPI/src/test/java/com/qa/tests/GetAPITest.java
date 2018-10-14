package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

	TestBase testBase;
	String serciveUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
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
	
	@Test(priority=1)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException{
		restClient=new RestClient();
		
		HashMap<String, String>headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		closeableHttpResponse=restClient.get(url,headerMap);
		
		//a. Status Code:
				int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status COde----->"+statusCode);
				Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
				//b. JSON String:
				String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
				
				JSONObject responseJson= new JSONObject(responseString);
				System.out.println("Response JSN from API--->"+responseJson);
				
				String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
				System.out.println("Value for json-->"+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 3);
				
				String totalValue=TestUtil.getValueByJPath(responseJson,"/total");
				System.out.println("Value for json-->"+totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//get the value from JSON Array:
				String last_name=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				System.out.println(last_name);
				
				
				//c.All Headers
				Header[] headerArray= closeableHttpResponse.getAllHeaders();
				
				HashMap<String,String> allHeaders= new HashMap<String, String>();
				
				for(Header header: headerArray){
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Header Array--->"+allHeaders);
				
				
		
		
		
	}
	
	
	@Test(priority=2)
	public void getAPITestWithoutHeader() throws ClientProtocolException, IOException{
		restClient=new RestClient();
		closeableHttpResponse=restClient.get(url);
		
		//a. Status Code:
				int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status COde----->"+statusCode);
				Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
				//b. JSON String:
				String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
				
				JSONObject responseJson= new JSONObject(responseString);
				System.out.println("Response JSN from API--->"+responseJson);
				
				String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
				System.out.println("Value for json-->"+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 3);
				
				String totalValue=TestUtil.getValueByJPath(responseJson,"/total");
				System.out.println("Value for json-->"+totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//get the value from JSON Array:
				String last_name=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				System.out.println(last_name);
				
				
				//c.All Headers
				Header[] headerArray= closeableHttpResponse.getAllHeaders();
				
				HashMap<String,String> allHeaders= new HashMap<String, String>();
				
				for(Header header: headerArray){
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Header Array--->"+allHeaders);
				
				
		
		
		
	}
	
	
	
	
}
