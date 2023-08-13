package com.wanted.preonboard.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
