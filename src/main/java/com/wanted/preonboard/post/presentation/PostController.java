package com.wanted.preonboard.post.presentation;

import com.wanted.preonboard.auth.Auth;
import com.wanted.preonboard.auth.UserContext;
import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.application.PostUpdateRequest;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import com.wanted.preonboard.post.dto.response.PostContentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 생성")
    @Auth
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid final CreatePostRequest request) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.createPost(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping("/feed")
    public ResponseEntity<List<PostContentResponse>> getFeedByPage(@RequestParam("page") final int page,
                                                                   @RequestParam("size") final int size) {
        return ResponseEntity.ok(postService.getFeedByPage(page, size));
    }

    @Operation(summary = "게시글 단건 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<PostContentResponse> getSinglePost(@PathVariable("postId") final Long postId) {
        return ResponseEntity.ok(postService.getSinglePost(postId));
    }

    @Operation(summary = "게시글 수정")
    @Auth
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") final Long postId,
                                           @RequestBody @Valid final PostUpdateRequest request) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.updatePost(memberId, postId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시글 삭제")
    @Auth
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") final Long postId) {
        final Long memberId = UserContext.CONTEXT.get();
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok().build();
    }
}
