package com.wanted.preonboard.post.application;

import com.wanted.preonboard.member.domain.Member;
import com.wanted.preonboard.post.domain.Post;
import com.wanted.preonboard.post.domain.PostRepository;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(final Long memberId, final CreatePostRequest request) {
        final Member member = new Member(
                memberId,
                "nickname",
                "email",
                "password"
        );
        final Post post = new Post(
                member,
                request.title(),
                request.content()
        );
        postRepository.save(post);
    }
}
