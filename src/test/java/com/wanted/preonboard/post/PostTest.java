package com.wanted.preonboard.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;

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
        postService.createPost(request);
        assertThat(postRepository.findAll()).hasSize(1);
    }

    private class PostService {
        private final PostRepository postRepository;

        public PostService(final PostRepository postRepository) {
            this.postRepository = postRepository;
        }


        public void createPost(final CreatePostRequest request) {
            final Long memberId = 1L;
            final Post post = new Post(
                    memberId,
                    request.title(),
                    request.content()
            );
            postRepository.save(post);
        }
    }

    private class PostRepository {
        HashMap<Long, Post> posts = new HashMap<>();
        public List<Post> findAll() {
            return List.copyOf(posts.values());
        }

        public void save(final Post post) {
            posts.put(post.memberId, post);
        }
    }

    private class Post {
        private final Long memberId;
        private final String title;
        private final String content;

        public Post(final Long memberId, final String title, final String content) {
            this.memberId = memberId;
            this.title = title;
            this.content = content;
        }
    }

    public record CreatePostRequest(String title, String content) {
        public CreatePostRequest {
            Assert.hasText(title, "title must not be empty");
            Assert.hasText(content, "content must not be empty");
        }
    }

}
