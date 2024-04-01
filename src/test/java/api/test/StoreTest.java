package api.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.storeEndPoints;
import api.payload.Store;
import api.utilities.BaseClass;
import io.restassured.response.Response;

/**
 * @author shrikrushna.sonkar
 *
 */
public class StoreTest extends BaseClass {
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
		
		test = extentCreateTest("Place an order for a pet");

		Response response = storeEndPoints.createStoreOrder(storePayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.info(response.asPrettyString().replace(",", ",<br>"));
		test.info("Place an order for a pet response code - "+response.getStatusCode());

		System.out.println("******************************************************");

	}
	
	@Test (priority = 2)
	public void testGetUserName() {
		test = extentCreateTest("find purchase order for a pet");

		Response response = storeEndPoints.readStoreOrder(this.storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		test.info(response.asPrettyString().replace(",", ",<br>"));
		test.info("find purchase order for a pet response code - "+response.getStatusCode());

		System.out.println("******************************************************");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		test = extentCreateTest("Delete purchase order for a pet");

		Response response = storeEndPoints.deleteStoreOrder(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		test.info("Delete purchase order for a pet response code -"+response.getStatusCode());

		System.out.println("******************************************************");

	}

}
