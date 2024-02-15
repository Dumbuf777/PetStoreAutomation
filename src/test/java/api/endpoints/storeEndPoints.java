package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class storeEndPoints {

	public static Response createStoreOrder(Store payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url2);

		return response;
	}

	public static Response readStoreOrder(String userName) {
		Response response = given().pathParam("orderId", userName).when().get(Routes.get_url2);

		return response;
	}

	public static Response updateStoreOrder(String userName, User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(Routes.update_url);

		return response;
	}

	public static Response deleteStoreOrder(String userName) {
		Response response = given().pathParam("orderId", userName).when().delete(Routes.delete_url2);

		return response;
	}


}
