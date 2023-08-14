package com.wanted.preonboard.post;

import com.wanted.preonboard.common.ApiTest;
import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.post.domain.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest extends ApiTest {

    @Autowired private PostRepository postRepository;

    @Test
    void createPost() {
        final String accessToken = Scenario.signUpMember().request()
                .signInMember().requestAndGetToken();

        Scenario.createPost().request(accessToken);

        assertThat(postRepository.findAll()).hasSize(1);
    }

}
