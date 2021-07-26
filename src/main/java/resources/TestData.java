package resources;

import java.util.ArrayList;
import java.util.Arrays;

import pojo.AddPlace;
import pojo.Location;

public class TestData {

	public AddPlace addPlace(String name, String language, String address) {
		AddPlace place = new AddPlace();
		Location loc = new Location();
		loc.setLat(35.5543);
		loc.setLng(38.5543);
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setName(name);
		place.setPhone_number("4455778899");
		place.setWebsite("googlemap");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("shoe park", "cloth park", "makeup"));
		place.setTypes(list);
		place.setLocation(loc);
		return place;
	}

	public String deletePlacePayload(String placeid) {
		return "{\r\n  \"place_id\":\""+placeid+"\"\r\n}";
	}
}

