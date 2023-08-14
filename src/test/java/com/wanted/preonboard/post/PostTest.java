package com.wanted.preonboard.post;

import com.wanted.preonboard.common.ApiTest;
import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.post.application.PostService;
import com.wanted.preonboard.post.domain.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    void getFeed() {
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

        assertThat(postRepository.findAll()).hasSize(4);
        assertThat(postService.getFeedByPage(0, 2)).hasSize(2);
        assertThat(postService.getFeedByPage(1, 2)).hasSize(2);
        assertThat(postService.getFeedByPage(2, 2)).isEmpty();
    }

}
