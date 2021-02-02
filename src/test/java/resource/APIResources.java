package resource;

//enum is a special class is java which has collections of constants or method
public enum APIResources {
	

	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	APIResources(String resource)
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
