package resource;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public static RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
				return req;
		}
		return req;
	}
	
	public static String getGlobalValues(String Key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Bala\\workspace\\APIFramework\\src\\test\\java\\resource\\global.properties");
		prop.load(fis);
		return prop.getProperty(Key);
		
	}
	
	public String getJsonPathResponse(Response response, String KeyValue)
	{

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(KeyValue).toString();
	}

}
