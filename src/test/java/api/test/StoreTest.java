/**
 * 
 */
package api.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.endpoints.storeEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

/**
 * @author shrikrushna.sonkar
 *
 */
public class StoreTest {
	Store storePayload;

	@BeforeClass
	public void setupStoreData() {
		storePayload = new Store();
		storePayload.setId(RandomStringUtils.randomNumeric(1));
		storePayload.setPetId(RandomStringUtils.randomNumeric(1));
		storePayload.setQuantity(RandomStringUtils.randomNumeric(1));
//		storePayload.setShipDate(RandomStringUtils.randomAlphabetic(1));
        storePayload.setStatus("placed");
		storePayload.setComplete(true);
	}
	
	@Test(priority = 1)
	public void testPostStore(){
		
		Response response = storeEndPoints.createStoreOrder(storePayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("******************************************************");

	}
	
	@Test (priority = 2)
	public void testGetUserName() {
		Response response = storeEndPoints.readStoreOrder(this.storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("******************************************************");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response response = storeEndPoints.deleteStoreOrder(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("******************************************************");

	}

}
