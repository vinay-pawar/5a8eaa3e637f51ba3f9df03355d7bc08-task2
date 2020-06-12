package step_defs;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import repos.APIResourceURI;

public class CreateBotSteps {

	org.apache.log4j.Logger logger;
	Response response;
	public static String baseURL = null;
	String cookie;
	String workflow_id;
	String csrf;

	@Given("base url {string} logs generated in {string} file")
	public void base_url_as_https_automate_io_logs_generated_in_file(String host, String logFile) {
		System.setProperty("file.name", logFile);
		RestAssured.useRelaxedHTTPSValidation();
		logger = Logger.getLogger(CreateBotSteps.class);
		baseURL = host;
		csrf = "41GPycMcFGZdDSnHibPBCnjo";
		logger.info("\n==============================Starting Test Create Gmail Bot using API==============================");
	}

	@When("hit post method for login with arguments email: {string} and password: {string}")
	public void hit_post_method_for_login_with_arguments_email_and_password(String email, String password) {
		logger.info("\n****************************************Login Process Initiated****************************************");
		logger.info("Login URI: "+baseURL + APIResourceURI.LOGIN_URI);
		logger.info("Request Payload: "+"{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}");
		logger.info("Content-Type: "+ContentType.JSON);
		logger.info("Accept: "+ContentType.JSON);
		response = given().cookie("_csrf", csrf).contentType(ContentType.JSON).accept(ContentType.JSON)
				.body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}").cookie("_csrf", csrf).when()
				.post(baseURL + APIResourceURI.LOGIN_URI);
	}

	@Then("response code should be {int} ok and status will be {string}")
	public void response_code_should_be_ok_and_status_will_be_success(int code, String status) {
		int actual_code=response.getStatusCode();
		String actual_status=response.jsonPath().get("status");
		logger.info("Response Body: "+response.asString());
		logger.assertLog(code==actual_code, "Response status code mismatch. Expected: "+code+" Actual: "+actual_code);
		logger.assertLog(status.equals(actual_status), "Response status mismatch. Expected: "+status+" Actual: "+actual_status);
		cookie = response.getCookie("connect.sid");
		logger.info("Response Cookie: {connect.sid : "+response.getCookie("connect.sid")+" }");
		logger.info("\n****************************************Login Process Ended****************************************");
	}

	@When("hit post method for Creating Bot to forward new email to {string}")
	public void hit_post_method_for_Creating_Bot_to_forward_new_email_to(String toEmail) throws IOException {
		logger.info("\n****************************************Bot Creation Process Initiated****************************************");
		String jsonBody = generateStringFromResource(
				System.getProperty("user.dir") + "/src/test/java/requests/workspacerequest.json").replace("?", toEmail)
						.replace("#", "" + (Math.random() * 99 + 1));
		Header reqHeader = new Header("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
		logger.info("Login URI: "+baseURL + APIResourceURI.WORKFLOW_URI);
		logger.info("Request Payload: \n"+jsonBody);
		logger.info("Content-Type: "+ContentType.JSON);
		logger.info("Request Cookie: {_csrf : "+csrf);
		logger.info("Request Cookie: {connect.sid : "+response.getCookie("connect.sid")+" }");
		logger.info("Accept: "+ContentType.JSON);
		logger.info("Header: {"+reqHeader.getName()+" : "+reqHeader.getValue()+" }");
		response = given().cookie("connect.sid", cookie).cookie("_csrf", csrf).contentType(ContentType.JSON)
				.accept(ContentType.JSON).header(reqHeader).body(jsonBody).when()
				.post(baseURL + APIResourceURI.WORKFLOW_URI);
	}

	@Then("response code should be {int} ok and status will be {string} and message as {string}")
	public void response_code_should_be_ok_and_status_will_be_success_and_message_as_Bot_successfully_stored(
			int code, String status, String message) {
		int actual_code=response.getStatusCode();
		String actual_status=response.jsonPath().get("status");
		Map<String, String> data = response.jsonPath().getMap("data");
		workflow_id = data.get("_id");
		String actual_message = ("message");
		logger.info("Response Body: "+response.asString());
		logger.assertLog(code==actual_code, "Response status code mismatch. Expected: "+code+" Actual: "+actual_code);
		logger.assertLog(status.equals(actual_status), "Response status mismatch. Expected: "+status+" Actual: "+actual_status);
		logger.assertLog(message.equals(actual_message), "Response status mismatch. Expected: "+message+" Actual: "+actual_message);
		logger.info("\n****************************************Bot Creation Process Ended****************************************");
	}

	@When("hit Get method to enable bot we created in previous step")
	public void get_method_to_enable_bot_we_created_in_previous_step() {
		logger.info("\n****************************************Bot Enabling Process Initiated****************************************");
		Header reqHeader = new Header("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
		logger.info("Login URI: "+baseURL + APIResourceURI.ENABLE_WORKFLOW_URI.replace("id", workflow_id));
		logger.info("Content-Type: "+ContentType.JSON);
		logger.info("Request Cookie: {_csrf : "+csrf);
		logger.info("Request Cookie: {connect.sid : "+response.getCookie("connect.sid")+" }");
		logger.info("Accept: "+ContentType.JSON);
		logger.info("Header: {"+reqHeader.getName()+" : "+reqHeader.getValue()+" }");
		logger.info("Query Parameter: "+"skipPremiumCheck = true");
		response = given().cookie("connect.sid", cookie).cookie("_csrf", csrf).contentType(ContentType.JSON)
				.accept(ContentType.JSON).header(reqHeader).queryParam("skipPremiumCheck", true).when()
				.post(baseURL + APIResourceURI.ENABLE_WORKFLOW_URI.replace("id", workflow_id));
	}

	@Then("response code should be {int} and status will be {string} and message as {string}")
	public void response_code_should_be_ok_and_status_will_be_success_and_message_as_Bot_successfully_enabled(
			int code, String status, String message) {
		logger.info("Response Body: "+response.asString());
		int actual_code=response.getStatusCode();
		String actual_status=response.jsonPath().get("status");
		Map<String, String> data = response.jsonPath().getMap("data");
		String actual_message = ("message");		
		logger.assertLog(code==actual_code, "Response status code mismatch. Expected: "+code+" Actual: "+actual_code);
		logger.assertLog(status.equals(actual_status), "Response status mismatch. Expected: "+status+" Actual: "+actual_status);
		logger.assertLog(message.equals(actual_message), "Response status mismatch. Expected: "+message+" Actual: "+actual_message);
		logger.info("\n****************************************Bot Enabling Process Ended****************************************");
	}

	@Then("logout the session and verify response code should be {int} ok and status will be {string} and message as {string}")
	public void logout_the_session_and_verify_message_Session_expired(int code, String status, String message) {
		logger.info("\n****************************************Logout Process Initiated****************************************");
		Header reqHeader = new Header("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
		logger.info("Login URI: "+baseURL + APIResourceURI.LOGOUT_URI);
		logger.info("Content-Type: "+ContentType.JSON);
		logger.info("Request Cookie: {_csrf : "+csrf);
		logger.info("Request Cookie: {connect.sid : "+response.getCookie("connect.sid")+" }");
		logger.info("Accept: "+ContentType.JSON);
		logger.info("Header: {"+reqHeader.getName()+" : "+reqHeader.getValue()+" }");
		response = given().cookie("connect.sid", cookie).cookie("_csrf", csrf).contentType(ContentType.JSON)
				.accept(ContentType.JSON).header(reqHeader).when().delete(baseURL + APIResourceURI.LOGOUT_URI);
		int actual_code=response.getStatusCode();
		String actual_status=response.jsonPath().get("status");
		Map<String, String> data = response.jsonPath().getMap("data");
		String actual_message = ("message");
		logger.info("Response Body: "+response.asString());
		logger.assertLog(code==actual_code, "Response status code mismatch. Expected: "+code+" Actual: "+actual_code);
		logger.assertLog(status.equals(actual_status), "Response status mismatch. Expected: "+status+" Actual: "+actual_status);
		logger.assertLog(message.equals(actual_message), "Response status mismatch. Expected: "+message+" Actual: "+actual_message);
		/*if (response.getStatusCode() == 200) {
			logger.info("Logout Successfully");
		} else {
			logger.info("logout fail due to following error :\n" + response.asString());
		}*/
		logger.info("\n****************************************Logout Process Ended****************************************");
	}

	public String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
