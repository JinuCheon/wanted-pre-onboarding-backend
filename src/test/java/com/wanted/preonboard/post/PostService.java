package com.wanted.preonboard.post;

import com.wanted.preonboard.member.domain.Member;

class PostService {
    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }


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
