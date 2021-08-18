package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.TestData;
import resources.URLResources;
import resources.Utils;

public class AddPlaceDefinations extends Utils {
	RequestSpecification givenRequest;
	Response addPlaceResponse;
	String stringResponse;
	static String placeId;
	TestData data = new TestData();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		givenRequest = given().spec(reqestSpecification()).body(data.addPlace(name, language, address));
	}

	@When("user calls {string} with {string} Http Request")
	public void user_calls_with_post_http_request(String resource, String method) {
		URLResources apiResources = URLResources.valueOf(resource);
		System.out.println(apiResources.getResource());
		if (method.equalsIgnoreCase("POST"))
			addPlaceResponse = givenRequest.when().post(apiResources.getResource());
		else if (method.equalsIgnoreCase("GET"))
			addPlaceResponse = givenRequest.when().get(apiResources.getResource());
		else if (method.equalsIgnoreCase("PUT"))
			addPlaceResponse = givenRequest.when().put(apiResources.getResource());
		else if (method.equalsIgnoreCase("DELETE"))
			addPlaceResponse = givenRequest.when().delete(apiResources.getResource());
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer StatusCode) {
		int statusCode1 = addPlaceResponse.getStatusCode();
		Assert.assertEquals(statusCode1, 200);
	}

	@Then("{string} code should be {string}")
	public void code_should_be(String key, String value) {

		Assert.assertEquals(getJsonPath(addPlaceResponse, key), value);
	}

	@Then("verify the place id created maps to {string} using {string}")
	public void verify_the_place_id_created_maps_to_using(String string, String string2) {
		placeId = getJsonPath(addPlaceResponse, "place_id");
		given().spec(givenRequest).queryParam("place_id", placeId);
	}

	@Given("User have delete payload")
	public void user_have_delete_payload() throws IOException {
		givenRequest = given().spec(reqestSpecification()).body(data.deletePlacePayload(placeId));

	}

}
