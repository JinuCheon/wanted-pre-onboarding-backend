package com.wanted.preonboard.post;

import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.domain.PostRepository;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    private PostRepository postRepository;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepository();
        postService = new PostService(postRepository);
    }

    @Test
    void createPost() {
        final CreatePostRequest request = new CreatePostRequest(
                "title",
                "content"
        );
        final Long memberId = 1L;
        postService.createPost(memberId, request);
        assertThat(postRepository.findAll()).hasSize(1);
    }

}
