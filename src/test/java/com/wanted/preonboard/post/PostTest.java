package com.wanted.preonboard.post;

import com.wanted.preonboard.common.ApiTest;
import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.domain.PostRepository;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest extends ApiTest {

    @Autowired private PostRepository postRepository;

    @Test
    void createPost() {
        final String accessToken = Scenario.signUpMember().request()
                .signInMember().requestAndGetToken();

        final CreatePostRequest request = new CreatePostRequest(
                "title",
                "content"
        );
        RestAssured.given().log().all()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/posts")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
        assertThat(postRepository.findAll()).hasSize(1);
    }

}
