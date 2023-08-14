package com.wanted.preonboard.member.api;

import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.member.dto.request.MemberSignUpRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class SignUpMemberApi {
    private String nickname = "nickname";
    private String email = "woojin8787@gmail.com";
    private String password = "password1234";

    public SignUpMemberApi nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public SignUpMemberApi email(final String email) {
        this.email = email;
        return this;
    }

    public SignUpMemberApi password(final String password) {
        this.password = password;
        return this;
    }

    public Scenario request() {
        final MemberSignUpRequest request = new MemberSignUpRequest(
                nickname,
                email,
                password
        );
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/members/sign-up")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
        return new Scenario();
    }
}
