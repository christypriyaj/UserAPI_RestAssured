package stepDefinition;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.util.Map;
import org.testng.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.Context;

public class PostStep {

	private Context context;

	public PostStep(Context context) {
		this.context = context;
	}

	RequestSpecification request;
	Response response;
	int respStatusCode, expStatusCode;
	String respStatusMsg;
	Map<String, Object> userData;
	String respStatusLine,expStatusLine, respStatus, respMessage, expRespMessage,expRespStatus;
	private Map<String, Object> requestData;
	private Map<String, Object> expectedData;

	@Given("user sets a post request with valid endpoint from {string} and {string}")
	public void user_sets_a_post_request_with_valid_endpoint_from_and(String scenario, String jsonFile) {

		File file = new File("src/test/resources/jsonData/" + jsonFile); //Loads JSON file
		ObjectMapper mapper = new ObjectMapper(); //Jackson class to convert JSON to Java Obj

		Map<String, Map<String, Object>> allData;
		try {
			allData = mapper.readValue(file, new TypeReference<Map<String, Map<String, Object>>>() {});
			userData = allData.get(scenario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		requestData = (Map<String, Object>) userData.get("request");
		expectedData = (Map<String, Object>) userData.get("expected");
		context.setExpectedData(expectedData);
		context.setExpectedData(expectedData);
		request = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json")
				.body(requestData);
	}

	@When("user send post request with valid first name,last name,contact number,email id,user address")
	public void user_send_post_request_with_valid_first_name_last_name_contact_number_email_id_user_address() {
		response = request.when().post("/createusers");
		context.setResponse(response);
	}

	@When("user send a post request with invalid data")
	public void user_send_a_post_request_with_invalid_data() {
		response = request.when().post("/createusers");
		context.setResponse(response);
	}
	
	@Then("user should receive correct content type")
	public void user_should_receive_correct_content_type() {
		Response response = context.getResponse();
		String actualContentType = response.getContentType();
		Assert.assertTrue(actualContentType.contains("application/json"));
		respStatusCode = response.getStatusCode();
		if (respStatusCode == 201) {
			Context.userId = response.jsonPath().getString("userId");
		}
		
	}
	
	@Then("user should receive correct status line")
	public void user_should_receive_correct_status_line() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respStatusLine = response.getStatusLine();
		expStatusLine = (String) expectedData.get("statusMsg");
		Assert.assertEquals(respStatusLine, expStatusLine);
		respStatusCode = response.getStatusCode();
		if (respStatusCode == 201) {
			Context.userId = response.jsonPath().getString("userId");
		}
	}
	
	@Then("user should get Error with correct status code")
	public void user_should_get_error_with_correct_status_code() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respStatusCode = response.getStatusCode();
		expStatusCode = (Integer) expectedData.get("statusCode");
		Assert.assertEquals(respStatusCode, expStatusCode);
	}

	@Then("user should get Error with correct error message")
	public void user_should_get_error_with_correct_error_message() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respMessage = response.jsonPath().getString("message");
		expRespMessage = (String) expectedData.get("responseErrorMessage");
		Assert.assertEquals(expRespMessage, respMessage);
	}
}
