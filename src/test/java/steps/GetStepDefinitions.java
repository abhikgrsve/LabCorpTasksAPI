package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class GetStepDefinitions {

	private Response response;

	@Given("I send a GET request to {string}")
	public void i_send_a_get_request_to(String url) {
		response = RestAssured.given().when().get(url).then().extract().response();
	}

	@Then("the status code should be {int}")
	public void the_status_code_should_be(int expectedStatusCode) {
		Assert.assertEquals(expectedStatusCode, response.getStatusCode());
	}

	@Then("the response should contain {string}")
	public void the_response_should_contain(String field) {
		response.then().body("$", hasKey(field));
	}

	@Then("the response should contain path, ip, and headers")
	public void the_response_should_contain_path_ip_and_headers() {
		response.then().body("$", hasKey("path"));
		response.then().body("$", hasKey("ip"));
		response.then().body("$", hasKey("headers"));

		String path = response.jsonPath().getString("path");
		String ip = response.jsonPath().getString("ip");
		String headers = response.jsonPath().getString("headers");

		System.out.println("Path: " + path);
		System.out.println("IP: " + ip);
		System.out.println("Headers: " + headers);

		Assert.assertNotNull(path);
		Assert.assertNotNull(ip);
		Assert.assertNotNull(headers);
	}

	private ResponseAwareMatcher<Response> hasKey(String field) {

		return null;
	}

}
