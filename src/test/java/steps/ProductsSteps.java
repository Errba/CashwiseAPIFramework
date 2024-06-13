package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class ProductsSteps {

    RequestSpecification request;
    // this can save your request, you can keep adding changes, and when it's ready, you can send it
    Response response;
    // and this one will save response

    // we put them above the class level, so that I can use them with different methods. It should be accessible


    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjAwMzM4NTEsImlhdCI6MTcxNzQ0MTg1MSwidXNlcm5hbWUiOiJlcmJvbGJha3RpaWFydXVsdUBnbWFpbC5jb20ifQ.a2oGxRaFmQkLjN_WPwsk5lPohYvlSQ77ZcippwfFagVWazCn7g03NvgkxU62Mur8Rotlh_PCiyuBTY6yoKvdyA";

    JSONObject requestBody = new JSONObject();

    @Given("base url {string}")
    public void base_url(String baseUrl) {

        request = RestAssured.given().baseUri(baseUrl).contentType(ContentType.JSON);

        // Rest assured is to automatically automate API
        // PostMan is about manually automating APi

    }

    @Given("I have access")
    public void i_have_access() {
        request = request.auth().oauth2(token);

    }

    @Given("I have the endpoint {string}")
    public void i_have_the_endpoint(String endPoint) {
        request = request.basePath(endPoint);

    }

    @Given("I have {string} with {string} in request body")
    public void i_have_with_in_request_body(String key, String value) {
        requestBody.put(key, value);


    }

    @When("I send post request")
    public void i_send_post_request() {
        response = request.body(requestBody.toString()).post(); // post here means - send button  in postman



    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {

        System.out.println(response.prettyPrint());

        Assert.assertEquals(statusCode, response.statusCode());

        // then we run our code to check, if it's working, if there are some issues, before you need to always check
        // manually, and then automation


    }

    @Then("verify I have {string} with {string} in request body")
    public void verify_i_have_with_in_request_body(String key, String value) {
        response.then()
                .body(key, equalTo(value));



    }

    @Then("I delete the product")
    public void i_delete_the_product() {

        String id = response.jsonPath().getString("product_id");

        response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .delete("/products/" + id);
        Assert.assertEquals(response.statusCode(), 201);
    }
}
