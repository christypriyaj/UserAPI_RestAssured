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

public class DeleteStep {
	private Context context;
	public DeleteStep(Context context) {
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
	
	@Given("user is creating DELETE request from {string} and {string}")
	public void user_is_creating_delete_request__from_and(String scenario, String jsonFile) throws Exception, DatabindException, IOException {
		File file = new File("src/test/resources/jsonData/" + jsonFile); //Loads JSON file
		ObjectMapper mapper = new ObjectMapper(); //Jackson class to convert JSON to Java Obj

		Map<String, Map<String, Object>> allData;
		allData = mapper.readValue(file, new TypeReference<Map<String, Map<String, Object>>>() {});
		userData = allData.get(scenario);
		requestData = (Map<String, Object>) userData.get("request");
		response = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json")
				.body(requestData)
				.when().post("/createusers");
		respStatusCode = response.getStatusCode();
		if (respStatusCode == 201) {
			Context.userId = response.jsonPath().getString("userId");
		}
		context.setRequestData(requestData);
		expectedData = (Map<String, Object>) userData.get("expected");
		context.setExpectedData(expectedData);
		request = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json");
	}

	@When("user send delete request with user Name")
	public void user_send_delete_request_with_user_name() {
		
		Map<String, Object> requestData = context.getRequestData();
		String userName = (String) requestData.get("userFirstName");
		response = request.when().delete("/deleteuser/username/"+userName);
		context.setResponse(response);
	}

	@Then("user should receive correct response message")
	public void user_should_receive_correct_response_message() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respMessage = response.jsonPath().getString("message");
		expRespMessage = (String) expectedData.get("responseMessage");
		Assert.assertEquals(expRespMessage, respMessage);
	}


	@When("user send delete request with valid user id")
	public void user_send_delete_request_with_valid_user_id() {
		response = request.when().delete("/deleteuser/"+Context.userId);
		context.setResponse(response);
	}

	@Given("user is creating DELETE request with invalid user from {string} and {string}")
	public void user_is_creating_delete_request_with_invalid_user_from_and(String scenario, String jsonFile) throws StreamReadException, DatabindException, IOException {
		File file = new File("src/test/resources/jsonData/" + jsonFile); //Loads JSON file
		ObjectMapper mapper = new ObjectMapper(); //Jackson class to convert JSON to Java Obj

		Map<String, Map<String, Object>> allData;
		allData = mapper.readValue(file, new TypeReference<Map<String, Map<String, Object>>>() {});
		userData = allData.get(scenario);
		requestData = (Map<String, Object>) userData.get("request");
		context.setRequestData(requestData);
		expectedData = (Map<String, Object>) userData.get("expected");
		context.setExpectedData(expectedData);
		request = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json");
	}
	
	@When("user send delete request with invalid user id")
	public void user_send_delete_request_with_invalid_user_id() {
		Map<String, Object> requestData = context.getRequestData();
		int userid = (Integer) requestData.get("userId");
		response = request.when().delete("/deleteuser/"+userid);
		context.setResponse(response);
	}
	
	@Then("user should receive correct error response message")
	public void user_should_receive_correct_error_response_message() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respMessage = response.jsonPath().getString("message");
		expRespMessage = (String) expectedData.get("responseMessage");
		Assert.assertTrue(respMessage.contains(expRespMessage));
	}
}
