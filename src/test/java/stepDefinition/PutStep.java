package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.Assert;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.Context;

public class PutStep {

	private Context context;

	public PutStep(Context context) {
		this.context = context;
	}

	RequestSpecification request;
	Response response;
	int respStatusCode, expStatusCode;
	String respStatusMsg;
	Map<String, Object> userData;
	String respStatusLine,expStatusLine, respStatus, respMessage, expRespMessage,expRespStatus,expectedUsername;
	private Map<String, Object> requestData;
	private Map<String, Object> expectedData;

	@Given("user is creating PUT request from {string} and {string}")
	public void user_is_creating_put_request_from_and(String scenario, String jsonFile) throws StreamReadException, DatabindException, IOException {
		File file = new File("src/test/resources/jsonData/" + jsonFile); //Loads JSON file
		ObjectMapper mapper = new ObjectMapper(); //Jackson class to convert JSON to Java Obj

		Map<String, Map<String, Object>> allData;
		allData = mapper.readValue(file, new TypeReference<Map<String, Map<String, Object>>>() {});
		userData = allData.get("validUser1");
		requestData = (Map<String, Object>) userData.get("request");
		response = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json")
				.body(requestData)
				.when().post("/createusers");
		respStatusCode = response.getStatusCode();
		if (respStatusCode == 201) {
			Context.userId = response.jsonPath().getString("userId");
		}
		userData = allData.get(scenario);
		requestData = (Map<String, Object>) userData.get("request");
		context.setRequestData(requestData);
		expectedData = (Map<String, Object>) userData.get("expected");
		context.setExpectedData(expectedData);
		request = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json")
				.body(requestData);
	}

	@When("user send put request")
	public void user_send_put_request() {
		response = request.when().put("/updateuser/"+Context.userId);
		context.setResponse(response);
	}


	@Then("user should validate updated field")
	public void user_should_validate_updated_field() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getRequestData();
		String actualUsername = response.jsonPath().getString("userLastName"); 
		expectedUsername = (String) expectedData.get("userLastName");
	    Assert.assertEquals(expectedUsername, actualUsername);
	}

}
