package resources;

public enum URLResources {
	addPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	URLResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
