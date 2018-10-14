package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

	TestBase testBase;
	String serciveUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	public PostAPITest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		testBase=new TestBase();
		serciveUrl= prop.getProperty("URL");
		apiUrl= prop.getProperty("serviceURL");
		
		url= serciveUrl+apiUrl;
		 
	}
	
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient=new RestClient();
		HashMap<String, String>headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson Api..
		ObjectMapper mapper= new ObjectMapper();
		Users user= new Users("Rehan","Leader");//expected user obj..
		
		//object to JSON file..
		mapper.writeValue(new File("C:\\Users\\User\\git\\RestAPI\\RestAPI\\src\\main\\java\\"
				+ "com\\qa\\data\\users.json"), user);
		
		//Object to json in String..
		String userStringJson=mapper.writeValueAsString(user);
		System.out.println(userStringJson);
		closeableHttpResponse=restClient.post(url, userStringJson, headerMap);
		
		//1. Status code..
		int status_code=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status_code, testBase.RESPONSE_STATUS_CODE_201);
		
		//2. Json String:
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("The Response from API"+responseJson);
		
		//Json to Java obj
		Users userRead=mapper.readValue(responseString, Users.class);//Actual user obj...
		System.out.println(userRead);
		
		Assert.assertTrue(user.getName().equals(userRead.getName()));
		Assert.assertTrue(user.getJob().equals(userRead.getJob()));
		
		System.out.println(userRead.getId());
		System.out.println(userRead.getCreatedAt());
		
		
	}
	
	
	
	
	
}
