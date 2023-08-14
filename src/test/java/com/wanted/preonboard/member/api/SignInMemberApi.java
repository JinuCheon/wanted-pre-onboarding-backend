package com.wanted.preonboard.member.api;

import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.member.dto.request.MemberSignInRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

public class SignInMemberApi {
    private String email = "test@gmail.com";
    private String password = "password1234";

    public SignInMemberApi email(final String email) {
        this.email = email;
        return this;
    }

    public SignInMemberApi password(final String password) {
        this.password = password;
        return this;
    }

    public Scenario request() {

        final MemberSignInRequest request = new MemberSignInRequest(
                email,
                password
        );

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/members/sign-in")
            .then().log().all()
            .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }

    public String requestAndGetToken() {

        final MemberSignInRequest request = new MemberSignInRequest(
                email,
                password
        );

        final ValidatableResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/members/sign-in")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
        return response.extract().body().jsonPath().getString("accessToken");
    }
}
