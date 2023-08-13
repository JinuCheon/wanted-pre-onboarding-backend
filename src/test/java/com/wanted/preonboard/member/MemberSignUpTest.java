package com.wanted.preonboard.member;

import com.wanted.preonboard.common.ApiTest;
import com.wanted.preonboard.member.domain.MemberRepository;
import com.wanted.preonboard.member.dto.request.MemberSignUpRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpTest extends ApiTest {

    @Autowired private MemberRepository memberRepository;

    @Test
    void signUp() {
        final String nickname = "nickname";
        final String email = "woojin8787@gmail.com";
        final String password = "password1234";

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

        assertThat(memberRepository.findAll()).hasSize(1);
    }

}
