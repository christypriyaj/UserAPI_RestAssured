package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import io.restassured.response.Response;

public class Context {
	public static String userId; 
	private String baseUrl;
    private String username;
    private String password;
    private Response response;
    private Map<String, Object> expectedData;
    private Map<String, Object> requestData;
    
    public Context() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/test/resources/conifgFile/config.properties"));
            this.baseUrl = props.getProperty("apiURL");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    
    public Map<String, Object> getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(Map<String, Object> expectedData) {
        this.expectedData = expectedData;
    }
    
    public void setRequestData(Map<String, Object> requestData) {
        this.requestData = requestData;
    }
    
    public Map<String, Object> getRequestData() {
        return requestData;
    }
}
