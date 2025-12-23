package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.ApiSpecs.requestSpecDemoWebShop;

public class AuthApiDemoWebShop {

    public static final String AUTH_COOKIE_NAME = "NOPCOMMERCE.AUTH";

    public static String login(String email, String password) {

        Response response = given(requestSpecDemoWebShop)
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", email)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .response();

        return response.getCookie(AUTH_COOKIE_NAME);
    }

    public static String[] loginWithCookie(String email, String password) {

        String cookieValue = login(email, password);

        return new String[]{AUTH_COOKIE_NAME, cookieValue};
    }
}
