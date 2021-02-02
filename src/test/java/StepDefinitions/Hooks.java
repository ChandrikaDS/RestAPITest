package StepDefinitions;

import java.io.IOException;

import cucumber.runtime.StepDefinition;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	
	public void beforeScenario() throws IOException
			{
				StepDefinitions m = new StepDefinitions();
				if(StepDefinitions.Place_id==null)
				{
					
					m.add_Place_Payload_with("chandu", "kannada", "Bengaluru");
					m.user_calls_UPI_with_http_request("AddPlaceAPI", "POST");
					m.verify_placeId_created_maps_to_using("chandu", "getPlaceAPI");
				}
				
			}

}
