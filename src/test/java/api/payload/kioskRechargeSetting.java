/**
 * 
 */
package api.payload;

import static io.restassured.RestAssured.given;

import api.endpoints.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 */
public class kioskRechargeSetting {

	public static Response createKioskRechargeSetting(KioskRecharegeSetting Kioskpayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Kioskpayload).when()
				.post(Routes.POST_KioskURL);

		return response;
	}

	public static Response readStoreOrder(String id) {
		Response response = given().pathParam("id", id).when().get(Routes.GET_KioskURL);

		return response;
	}

	public static Response updateStoreOrder(String id, KioskRecharegeSetting payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", id).body(payload).when().put(Routes.PUT_KioskURL);

		return response;
	}

	public static Response deleteStoreOrder(String id) {
		Response response = given().pathParam("orderId", id).when().delete(Routes.DELETE_KioskURL);

		return response;
	}
}
