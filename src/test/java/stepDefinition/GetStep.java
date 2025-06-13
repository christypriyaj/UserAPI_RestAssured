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

public class GetStep {

	private Context context;

	public GetStep(Context context) {
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

	@Given("user is creating GET request")
	public void user_is_creating_get_request() {
		request = given().auth().basic(context.getUsername(), context.getPassword())
				.header("Content-Type", "application/json");
	}

	@When("user performs GET operation with Endpoint  \\/users")
	public void user_performs_get_operation_with_endpoint_users() {
		response = request.when().get("/users");
		context.setResponse(response);
	}

	@Then("user should receive {int} ok valid status code")
	public void user_should_receive_ok_vaild_status_code(Integer int1) {
		respStatusCode = response.getStatusCode();
		Assert.assertEquals(respStatusCode, int1);
	}

	@Then("user should receive correct status line {string}")
	public void user_should_receive_correct_status_line(String expStatusLine) {
		respStatusLine = response.getStatusLine();
		Assert.assertEquals(respStatusLine, expStatusLine);
	}

	@Then("user should receive response body in the format  Content- Type JSON")
	public void user_should_receive_response_body_in_the_format_content_type_json() {
		String contentType = response.getHeader("Content-Type");
		Assert.assertEquals("application/json", contentType);
	}

	@Given("user is creating GET request from {string} and {string}")
	public void user_is_creating_get_request_from_and(String scenario, String jsonFile) {
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
		request = given().auth().basic(context.getUsername(), context.getPassword())
				.contentType("application/json");
	}

	@When("user performs GET operation with Endpoint user\\/userID")
	public void user_performs_get_operation_with_endpoint_user_user_id() {
		int userid = (Integer) requestData.get("userId");
		response = request.when().get("/user/"+userid);
		context.setResponse(response);
	}

	@Then("user should receive correct status code")
	public void user_should_receive_correct_status_code() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respStatusCode = response.getStatusCode();
		expStatusCode = (Integer) expectedData.get("statusCode");
		Assert.assertEquals(respStatusCode, expStatusCode);
		if (respStatusCode == 201) {
			Context.userId = response.jsonPath().getString("userId");
		}
	}

	@Then("Status line should contain error message")
	public void status_line_should_contain_error_message() {
		Response response = context.getResponse();
		Map<String, Object> expectedData = context.getExpectedData();
		respMessage = response.jsonPath().getString("message");
		expRespMessage = (String) expectedData.get("responseErrorMessage");
		Assert.assertEquals(expRespMessage, respMessage);
	}
	
	@When("user performs GET operation with Endpoint users\\/username\\/userFirstName")
	public void user_performs_get_operation_with_endpoint_users_username_user_first_name() {
		String userName = (String) requestData.get("userFirstname");
		response = request.when().get("/users/username/"+userName);
		context.setResponse(response);
	}

}
