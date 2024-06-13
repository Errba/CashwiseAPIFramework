import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class IntroToPostRequest {
    public static void main(String[] args) {


        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjAwMzM4NTEsImlhdCI6MTcxNzQ0MTg1MSwidXNlcm5hbWUiOiJlcmJvbGJha3RpaWFydXVsdUBnbWFpbC5jb20ifQ.a2oGxRaFmQkLjN_WPwsk5lPohYvlSQ77ZcippwfFagVWazCn7g03NvgkxU62Mur8Rotlh_PCiyuBTY6yoKvdyA";

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_title", "Supplements");
        requestBody.put("product_price", 14);
        requestBody.put("service_type_id", 1);
        requestBody.put("category_id", 1);
        requestBody.put("product_description", "Supplements to get healthy");
        requestBody.put("date_of_payment", "2024-06-04");
        requestBody.put("remind_before_day", 0);
        requestBody.put("do_remind_every_month", "REPEAT_EVERY_MONTH");

        RestAssured.given()
                .contentType(ContentType.JSON)
                // Content type - it's a format of data that we send to the HTTP
                .accept(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBody.toString())
                .when()
                .post("/myaccount/products")
                .then()
                .statusCode(201)
                .body("product_title", equalTo("Supplements"))
                .body("product_price", equalTo(14));


//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .auth()
//                .oauth2(token)
//                .baseUri()
//                .body(requestBody.toString())
//                .when()
//                .post("endpoint");
//
//

    }
}
