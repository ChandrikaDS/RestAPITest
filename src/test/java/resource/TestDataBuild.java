package resource;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String Address) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setAddress(Address);
		p.setLanguage(language);
		p.setPhone_number("8972573285");
		p.setWebsite("https://rahulshettyacademy.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoepark");
		myList.add("playpark");
		p.setTypes(myList);
		Location l = new Location();
		l.setLng(-43.23);
		l.setLat(-32.23);
		p.setLocation(l);
		return p;

}
	
	public String DeletePlace(String Place_id) {
		
	return "{\r\n" + 
			"    \"place_id\":\""+Place_id+"\"\r\n" + 
			"}";
	}
}

