/**
 * 
 */
package api.test;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.endpoints.UserEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import io.restassured.response.Response;

/**
 * @author shrikrushna.sonkar
 *
 */
public class PetTests {
	
	Faker faker;
	Pet petPayload;
	Category category;
	Tag tags;
	private List<String> photoUrls;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		petPayload = new Pet();
		petPayload.setId(faker.idNumber().hashCode());
		petPayload.setCategory(category);
		petPayload.setName(faker.name().name());
		petPayload.setTags((List<Tag>) tags);
		petPayload.setPhotoUrls(photoUrls);
		petPayload.setStatus("available");
		
//		JSONObject category = new JSONObject();
//        category.put("id", 0);
//        category.put("name", "string");
//
//        JSONArray photoUrls = new JSONArray();
//        photoUrls.add("string");
//
//        JSONObject tag = new JSONObject();
//        tag.put("id", 0);
//        tag.put("name", "string");
//
//        JSONArray tags = new JSONArray();
//        tags.add(tag);
//
//        JSONObject petpayload = new JSONObject();
//        petpayload.put("id", 0);
//        petpayload.put("category", category);
//        petpayload.put("name", "doggie");
//        petpayload.put("photoUrls", photoUrls);
//        petpayload.put("tags", tags);
//        petpayload.put("status", "available");
//
//        System.out.println(petpayload.toString());
	}
	
	@Test(priority = 1)
	public void testPostPet() {
		
		Response response = PetEndPoints.addPetToStore(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("**********************************************************");
	}
	
	@Test (priority = 2)
	public void testGetUserName() {
		Response response = PetEndPoints.readPets(this.petPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("**********************************************************");
	}
	
	
	
	@Test (priority = 3)
	public void testUpdatetPet() {
		
		// update data using pet payoad 
		petPayload.setName(faker.name().name());
		petPayload.setStatus("unavailable");

		
		Response response = PetEndPoints.updatePets(this.petPayload.getId(), petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("**********************************************************");
	}

	@Test(priority = 4)
	public void testDeletePetById() {
		Response response = PetEndPoints.deletePets(this.petPayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("**********************************************************");
	}
	
	
}
