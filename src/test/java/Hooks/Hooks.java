package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utility.Context;

import static io.restassured.RestAssured.*;

public class Hooks {

	//ConfigFileReader configFileReader = new ConfigFileReader();

	private static Context context;

	public Hooks(Context context) {
		this.context = context;
	}

	@Before
	public void setup() {
		baseURI = context.getBaseUrl();
	}

	@After
	public static void teardown(Scenario scenario) throws Throwable {
		String userId = context.getUserId();
		if (userId != null) {
			given()
			.auth().basic(context.getUsername(), context.getPassword())
			.when()
			.delete("/deleteuser/" + userId); 
			context.userId = null;
		}
	}

}
