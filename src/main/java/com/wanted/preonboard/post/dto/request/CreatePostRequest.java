package com.wanted.preonboard.post.dto.request;

import org.springframework.util.Assert;

public record CreatePostRequest(String title, String content) {
    public CreatePostRequest {
        Assert.hasText(title, "title must not be empty");
        Assert.hasText(content, "content must not be empty");
    }
}
