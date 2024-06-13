import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.Seller;
import pojo.Tag;

import java.util.Arrays;


public class IntroToSerialization {
    public static void main(String[] args) {

        // 1. create tag

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjAwMzM4NTEsImlhdCI6MTcxNzQ0MTg1MSwidXNlcm5hbWUiOiJlcmJvbGJha3RpaWFydXVsdUBnbWFpbC5jb20ifQ.a2oGxRaFmQkLjN_WPwsk5lPohYvlSQ77ZcippwfFagVWazCn7g03NvgkxU62Mur8Rotlh_PCiyuBTY6yoKvdyA";

        // 2. create pojo object

        Tag tag = new Tag();
        tag.setName_tag("pineapple");
        tag.setDescription("this is pineapple tag");

        // 3. serialize pojo object to json object

        Gson gson = new Gson();
        String requestBodyInJson = gson.toJson(tag);
        System.out.println(requestBodyInJson);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON) // content type is like headers - you got to provide
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBodyInJson)
                .when()
                .post("/myaccount/tags");

        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());


        // 4. deserialize json to java

        String responseInJson = response.asString();
        gson = new Gson();
        Tag tagResponse = gson.fromJson(responseInJson, Tag.class);

      //  Assert.assertTrue(tagResponse.getName_tag().equals(tag.getName_tag()));

        System.out.println(tagResponse.getMessage()); // it'll print - Internal server error

        System.out.println(Arrays.toString(tagResponse.getDetails())); // it'll print - [Тег уже существует]



        // next part - create seller

        Faker faker = new Faker();


        // 1. Create pojo object


        Seller seller = new Seller();
        seller.setCompany_name(faker.company().name());
        seller.setSeller_name(faker.name().fullName());
        seller.setEmail(faker.internet().emailAddress());
        seller.setPhone_number(faker.phoneNumber().cellPhone());
        seller.setAddress(faker.address().streetAddress());

        // 2. serialize pojo object to json object

        gson = new Gson();
        String requestInJson = gson.toJson(seller, Seller.class);


        // 3. Send request

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestInJson)
                .when()
                .post("/sellers");


        // 4. deserialize from json to java
        responseInJson = response.asString();
        gson = new Gson();
        Seller responseSeller = gson.fromJson(responseInJson, Seller.class);

        // verify status code

        Assert.assertEquals(response.statusCode(), 201);

        // verify response body

        Assert.assertTrue(responseSeller.getSeller_id() != 0);
        Assert.assertEquals(responseSeller.getCompany_name(), seller.getCompany_name());


    }
}
