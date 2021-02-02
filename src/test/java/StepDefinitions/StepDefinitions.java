package StepDefinitions;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resource.APIResources;
import resource.TestDataBuild;
import resource.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification res;
	
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String Place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address)  throws IOException {
		
		 res = given().spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
		
	}

	@When("user calls {string} UPI with {string} http request")
	public void user_calls_UPI_with_http_request(String resource, String method) {
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("post"))
		 response = res.when().post(resourceAPI.getResource());
		 else if(method.equalsIgnoreCase("get"))
			 response = res.when().get(resourceAPI.getResource());
		 else if(method.equalsIgnoreCase("delete"))
			 response = res.when().delete(resourceAPI.getResource());
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPathResponse(response, keyValue), expectedValue);
		System.out.println(keyValue);
		System.out.println(expectedValue);
	}
	
	@Then("Verify placeId created maps to {string} using {string}")
	public void verify_placeId_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		Place_id = getJsonPathResponse(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", Place_id);
		user_calls_UPI_with_http_request(resource,"get");
		String ActualName = getJsonPathResponse(response,"name");
		assertEquals(ActualName, expectedName);
		
	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		
		res = given().spec(requestSpecification()).body(data.DeletePlace(Place_id)).
				queryParam("place_id", Place_id);
	}

}
