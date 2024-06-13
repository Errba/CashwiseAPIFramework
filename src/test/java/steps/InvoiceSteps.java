package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;


public class InvoiceSteps {

    RequestSpecification request;
    Response response;

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjAwMzM4NTEsImlhdCI6MTcxNzQ0MTg1MSwidXNlcm5hbWUiOiJlcmJvbGJha3RpaWFydXVsdUBnbWFpbC5jb20ifQ.a2oGxRaFmQkLjN_WPwsk5lPohYvlSQ77ZcippwfFagVWazCn7g03NvgkxU62Mur8Rotlh_PCiyuBTY6yoKvdyA";

    JSONObject requestBody = new JSONObject();


    @Given("our base url {string}")
    public void our_base_url(String baseUrl) {

        request = RestAssured.given().baseUri(baseUrl).contentType(ContentType.JSON);


    }
    @Given("I got access")
    public void i_got_access() {

        request = request.auth().oauth2(token);


    }
    @Given("I got the endpoint {string}")
    public void i_got_the_endpoint(String endPoint) {

        request = request.basePath(endPoint);


    }
    @Given("I got {string} with {string} in request body")
    public void i_got_with_in_request_body(String key, String value) {

        // my code was request.put(key, value)
        // here was error
        // Invalid number of path parameters. Expected 0, was 1
        // then I changed to this, and it's working

        request.queryParam(key,value);

    }

    @When("I will send post request")
    public void i_will_send_post_request() {

        response = request.body(requestBody.toString()).post();


    }
    @Then("Verify status code equals {int}")
    public void verify_status_code_equals(Integer statusCode) {

     System.out.println(response.asPrettyString());

       Assert.assertEquals(statusCode, 201);


    }
    @Then("Validate I got {string} with {string} in request body")
    public void validate_i_got_with_in_request_body(String key, String value) {

        response.then()
                .body(key, equalTo(value));

//        response.then()
//                .body(key, equalTo(value));
//        requestBody.put("invoice_title", "AutoBody");



    }

    @Then("I remove the invoice")
    public void i_remove_the_invoice() {

        String id = response.jsonPath().getString("invoice_title");
        response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .delete("/invoices" + id);

        //Assert.assertEquals(response.statusCode(), 201);


    }
}
