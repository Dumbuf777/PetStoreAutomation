/**
 * 
 */
package api.test;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author shrikrushna.sonkar
 *
 */
public class UpdateAllPlayersPassword {
	public String accessToken;
	public List<String> ids;

	@Test(priority = 1)
	public void POST_AdminLogin() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("username", "systemadmin");
		requestBody.put("password", "Gameium@1234");
		Response response = sendPostRequestWithAuthorization("auth/v1/login", requestBody);
	
		JsonPath jsonpath = response.jsonPath().prettyPeek();
		accessToken = jsonpath.getString("accessToken");
		System.out.println("Game Session Id is " + accessToken);
		// Status code verification
		int statusResponseCode = response.getStatusCode();
		System.out.println("Status code is " + statusResponseCode);
		Assert.assertEquals(statusResponseCode, 200);
	}
	
	@Test(priority = 2)
	public void POST_SearchUser() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("text", "Raphael11694");
		JSONObject requestBody1 = new JSONObject();
		requestBody.put("password", "Gameium@1234");
		Response response = sendPostRequestWithAuthorization("auth/v1/user/search", requestBody, accessToken);
		JsonPath jsonpath = response.jsonPath().prettyPeek();
//		accessToken = jsonpath.getString("data.map(user => user.id)");
		ids = response.jsonPath().getList("data.id");

        // Print the ids
        for (String id : ids) {
            System.out.println("ID: " + id);
            Response response1 =sendPutRequestWithAuthorization(requestBody1, id);
            int statusResponseCode = response1.getStatusCode();
    		System.out.println("Status code is " + statusResponseCode);
        }
		// Status code verification
		int statusResponseCode = response.getStatusCode();
		System.out.println("Status code is " + statusResponseCode);
		Assert.assertEquals(statusResponseCode, 200);
		
	}
	
	//@Test(priority = 2)
	public void PUT_UpdatePassword() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("password", "Gameium@1234");
//      // Print the ids
      for (String id : ids) {
          System.out.println("ID: " + id);
          
      }
		Response response = sendPutRequestWithAuthorization( requestBody, accessToken);
		
		int statusResponseCode = response.getStatusCode();
		System.out.println("Status code is " + statusResponseCode);
		Assert.assertEquals(statusResponseCode, 200);
	}
	
	public Response sendPutRequestWithAuthorization(JSONObject requestBody, String ids) {
		RestAssured.baseURI = "https://qaautomation-api.cosmoslots.tech";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.auth().oauth2(accessToken); // Set the authorization header
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());
		return httpRequest.request(Method.PUT, "auth/v1/user/change-password/"+ids);
	}
	
	
	
	public Response sendPostRequestWithAuthorization(String path, JSONObject requestBody, String authToken) {
		RestAssured.baseURI = "https://qaautomation-api.cosmoslots.tech/";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.auth().oauth2(authToken); // Set the authorization header
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());
		return httpRequest.request(Method.POST, path);
	}

	public Response sendPostRequestWithAuthorization(String path, JSONObject requestBody) {
		RestAssured.baseURI = "https://qaautomation-api.cosmoslots.tech/";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());
		return httpRequest.request(Method.POST, path);
	}
}
