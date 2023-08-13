package com.wanted.preonboard.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepository();
    }

    @Test
    void createPost() {
        final CreatePostRequest request = new CreatePostRequest(
                "title",
                "content"
        );
        postService.createPost(request);
        assertThat(postRepository.findAll()).hasSize(1);
    }

    private class PostRepository {
        HashMap<Long, Post> posts = new HashMap<>();
        public List<Post> findAll() {
            return List.copyOf(posts.values());
        }
    }

    private class Post {
    }

    public record CreatePostRequest(String title, String content) {
        public CreatePostRequest {
            Assert.hasText(title, "title must not be empty");
            Assert.hasText(content, "content must not be empty");
        }
    }
}
