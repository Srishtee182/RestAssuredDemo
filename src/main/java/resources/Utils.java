package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification req;
	static Properties prop;
	static FileInputStream file = null;

	public RequestSpecification reqestSpecification() throws IOException {
		if (req == null) {
			PrintStream print = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getProperties("baseurl")).addQueryParam("Key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(print))
					.addFilter(ResponseLoggingFilter.logResponseTo(print)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getProperties(String key) throws IOException {
		prop = new Properties();
		file = new FileInputStream("constantdata.properties");
		prop.load(file);
		return prop.getProperty(key);

	}
	public static String getJsonPath(Response response, String key) {
		String Response = response.asString();
		JsonPath js = new JsonPath(Response);
		return js.get(key).toString();
	}
}
