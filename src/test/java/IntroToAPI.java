import io.restassured.RestAssured;

public class IntroToAPI {
    public static void main(String[] args) {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg0NTczMjksImlhdCI6MTcxNTg2NTMyOSwidXNlcm5hbWUiOiJlcmJvbGJha3RpaWFydXVsdUBnbWFpbC5jb20ifQ.geYLtDjTBTFUlAWDQHGiDRHM-RsTjAi1VutEWm8EkY5mVrUpdu92mnG4AkweD6pOz8rWFDM0ijkI_WCxFSOuHw";


        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/sellers/all")
                .then()
                .statusCode(200);


       RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/tags/all")
                .then()
                .statusCode(200);


        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .and()
                .queryParam("page", "1")
                .queryParam("size", 4)
                .when()
                .get("/myaccount/reminder/requests")
                .then()
                .statusCode(200);


        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/reminder/notifications")
                .then()
                .statusCode(200);



    }

}
