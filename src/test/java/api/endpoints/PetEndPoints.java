package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;;

public class PetEndPoints {

	// create perform curd operation
	//

	public static Response addPetToStore(Pet petPayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPayload).when()
				.post(Routes.post_url1);

		return response;
	}

	public static Response readPets(long id) {
		Response response = given().pathParam("petId", id).when().get(Routes.get_url1);

		return response;
	}

	public static Response updatePets(long id , Pet petPayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("petId", id).body(petPayload).when().put(Routes.update_url1);

		return response;
	}

	public static Response deletePets(long id) {
		Response response = given().pathParam("petId", id).when().delete(Routes.delete_url1);

		return response;
	}
}
