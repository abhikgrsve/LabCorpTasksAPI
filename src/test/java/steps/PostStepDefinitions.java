package steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostStepDefinitions {

	private Response response;

	@Given("I send a POST request to {string} with the order details")
	public void i_send_a_post_request_to_with_the_order_details(String url) {

		String payload = "payload";

		response = RestAssured.given().header("Content-Type", "application/json").body(payload).post(url).then()
				.extract().response();
	}

	@Then("the status code should be {int}")
	public void the_status_code_should_be(int expectedStatusCode) {

		Assert.assertEquals(expectedStatusCode, response.getStatusCode());
	}

	@Then("the order should contain correct customer information")
	public void the_order_should_contain_correct_customer_information() {

		Map<String, ?> customer = response.jsonPath().getMap("customer");
		Assert.assertEquals("Jane Smith", customer.get("name"));
		Assert.assertEquals("janesmith@example.com", customer.get("email"));
		Assert.assertEquals("1-987-654-3210", customer.get("phone"));

		Map<String, ?> address = (Map<String, ?>) customer.get("address");
		Assert.assertEquals("456 Oak Street", address.get("street"));
		Assert.assertEquals("Metropolis", address.get("city"));
		Assert.assertEquals("NY", address.get("state"));
		Assert.assertEquals("10001", address.get("zipcode"));
		Assert.assertEquals("USA", address.get("country"));
	}

	@Then("the order should contain correct payment details")
	public void the_order_should_contain_correct_payment_details() {

		Map<String, ?> payment = response.jsonPath().getMap("payment");
		Assert.assertEquals("credit_card", payment.get("method"));
		Assert.assertEquals("txn_67890", payment.get("transaction_id"));
		Assert.assertEquals(111.97, payment.get("amount"));
		Assert.assertEquals("USD", payment.get("currency"));
	}

	@Then("the order should contain correct product details")
	public void the_order_should_contain_correct_product_details() {

		List<Map<String, ?>> items = response.jsonPath().getList("items");

		Map<String, ?> item1 = items.get(0);
		Assert.assertEquals("A101", item1.get("product_id"));
		Assert.assertEquals("Wireless Headphones", item1.get("name"));
		Assert.assertEquals(1, item1.get("quantity"));
		Assert.assertEquals(79.99, item1.get("price"));

		Map<String, ?> item2 = items.get(1);
		Assert.assertEquals("B202", item2.get("product_id"));
		Assert.assertEquals("Smartphone Case", item2.get("name"));
		Assert.assertEquals(2, item2.get("quantity"));
		Assert.assertEquals(15.99, item2.get("price"));
	}

}
