package com.wanted.preonboard.post;

import com.wanted.preonboard.common.ApiTest;
import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.domain.PostRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest extends ApiTest {

    @Autowired private PostRepository postRepository;
    @Autowired private PostService postService;

    @Test
    void createPost() {
        final String accessToken = Scenario.signUpMember().request()
                .signInMember().requestAndGetToken();

        Scenario.createPost().request(accessToken);

        assertThat(postRepository.findAll()).hasSize(1);
    }

    @Test
    void getFeedByPage() {
        final String accessToken = Scenario.signUpMember().request()
                .signInMember().requestAndGetToken();

        Scenario.createPost()
                    .title("first title")
                    .content("first content")
                    .request(accessToken)
                .createPost()
                    .title("second title")
                    .content("second content")
                    .request(accessToken)
                .createPost()
                    .title("third title")
                    .content("third content")
                    .request(accessToken)
                .createPost()
                    .title("fourth title")
                    .content("fourth content")
                    .request(accessToken);

        ValidatableResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 2)
                .when()
                .get("/posts/feed")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
        assertThat(response.extract().jsonPath().getList(".")).hasSize(2);

        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("size", 2)
                .when()
                .get("/posts/feed")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
        assertThat(response.extract().jsonPath().getList(".")).hasSize(2);

        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("page", 2)
                .queryParam("size", 2)
                .when()
                .get("/posts/feed")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
        assertThat(response.extract().jsonPath().getList(".")).isEmpty();
    }

    @Test
    void getSinglePost() {
        final String accessToken = Scenario.signUpMember().request()
                .signInMember().requestAndGetToken();
        Scenario.createPost().request(accessToken);

        final Long postId = 1L;
        final ValidatableResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts/{postId}", postId)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
        assertThat(response.extract().jsonPath().getLong("postId")).isEqualTo(postId);
    }

    @Test
    void updatePost() {
        final String accessToken = Scenario.signUpMember().request()
                .signInMember().requestAndGetToken();
        Scenario.createPost().request(accessToken);

        final Long postId = 1L;
        final Long memberId = 1L;
        final String updatedContent = "updated content";
        final String updatedTitle = "updated title";

        postService.updatePost(memberId, postId, updatedTitle, updatedContent);

        assertThat(postRepository.findById(postId).get().getContent()).isEqualTo(updatedContent);
    }
}
