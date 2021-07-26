package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	AddPlaceDefinations def = new AddPlaceDefinations();

	@Before("@DeletePlace")
	public void beforeDelete() throws IOException {
		if (def.placeId == null) {
			def.add_place_payload("Dummyname", "Dummyaddress", "Dummylanguage");
			def.user_calls_with_post_http_request("addPlaceAPI", "POST");
			def.verify_the_place_id_created_maps_to_using("Dummyname", "getPlaceAPI");
		}
	}
}
