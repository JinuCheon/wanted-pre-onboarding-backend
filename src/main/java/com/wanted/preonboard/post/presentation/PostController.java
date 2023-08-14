package com.wanted.preonboard.post.presentation;

import com.wanted.preonboard.auth.UserContext;
import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid final CreatePostRequest request) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.createPost(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
