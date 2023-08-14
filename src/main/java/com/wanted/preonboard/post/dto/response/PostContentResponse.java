package com.wanted.preonboard.post.dto.response;

import com.wanted.preonboard.post.domain.Post;

public record PostContentResponse(
        Long postId,
        String title,
        String content,
        Long memberId,
        String memberNickname
) {
    public static PostContentResponse from(Post post) {
        return new PostContentResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getMember().getNickname()
        );
    }
}
