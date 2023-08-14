package com.wanted.preonboard.post.api;

import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class CreatePostApi {

    private String title = "title";
    private String content = "content";

    public CreatePostApi title(final String title) {
        this.title = title;
        return this;
    }

    public CreatePostApi content(final String content) {
        this.content = content;
        return this;
    }

    public Scenario request(final String accessToken) {
        final CreatePostRequest request = new CreatePostRequest(
                title,
                content
        );
        RestAssured.given().log().all()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/posts")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
        return new Scenario();
    }
}