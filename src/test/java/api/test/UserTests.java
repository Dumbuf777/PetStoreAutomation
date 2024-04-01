package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.BaseClass;
import io.restassured.response.Response;

public class UserTests  extends BaseClass{
	
	Faker faker;
	User userPayload;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostUser(){
		test = extentCreateTest("Create user");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		test.info(response.asPrettyString().replace(",", ",<br>"));
		Assert.assertEquals(response.getStatusCode(), 200);
		test.info("Create user response code - "+response.getStatusCode());
		long responseTime = response.getTime();
		System.out.println("Response time is " + responseTime);
		if (responseTime < 3000) {
			test.pass("Response status " + responseTime);
		} else {
			test.fail("Response status " + responseTime);
		}
		System.out.println("*********************************************************************");

	}
	
	@Test (priority = 2)
	public void testGetUserName() {
		test = extentCreateTest("Get user");

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.info(response.asPrettyString().replace(",", ",<br>"));
		test.info("Get user response code -"+response.getStatusCode());
		long responseTime = response.getTime();
		System.out.println("Response time is " + responseTime);
		if (responseTime < 3000) {
			test.pass("Response time " + responseTime);
		} else {
			test.fail("Response time " + responseTime);
		}

		System.out.println("**********************************************************************");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		test = extentCreateTest("Update user");

		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		response.then().log().body().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Check data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
//		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		test.info(response.asPrettyString().replace(",", ",<br>"));
		test.info("Update user response code -"+response.getStatusCode());
		
		long responseTime = response.getTime();
		System.out.println("Response time is " + responseTime);
		if (responseTime < 3000) {
			test.pass("Response time " + responseTime);
		} else {
			test.fail("Response time " + responseTime);
		}

		System.out.println("**********************************************************************");

	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		test = extentCreateTest("Delete user");

		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		test.info("Delete user response code -"+response.getStatusCode());
		
		long responseTime = response.getTime();
		System.out.println("Response time is " + responseTime);
		if (responseTime < 3000) {
			test.pass("Response time " + responseTime);
		} else {
			test.fail("Response time " + responseTime);
		}
		System.out.println("******************************************************");

	}
	
	
}
