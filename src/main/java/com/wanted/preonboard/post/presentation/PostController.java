package com.wanted.preonboard.post.presentation;

import com.wanted.preonboard.auth.Auth;
import com.wanted.preonboard.auth.UserContext;
import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.application.PostUpdateRequest;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import com.wanted.preonboard.post.dto.response.PostContentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Auth
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid final CreatePostRequest request) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.createPost(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostContentResponse>> getFeedByPage(@RequestParam("page") final int page,
                                                                   @RequestParam("size") final int size) {
        return ResponseEntity.ok(postService.getFeedByPage(page, size));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostContentResponse> getSinglePost(@PathVariable("postId") final Long postId) {
        return ResponseEntity.ok(postService.getSinglePost(postId));
    }

    @Auth
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") final Long postId,
                                           @RequestBody @Valid final PostUpdateRequest request) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.updatePost(memberId, postId, request);
        return ResponseEntity.ok().build();
    }

    @Auth
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") final Long postId) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok().build();
    }

}
