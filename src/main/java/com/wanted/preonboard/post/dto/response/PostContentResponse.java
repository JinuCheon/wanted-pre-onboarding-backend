package com.wanted.preonboard.post.dto.response;

import com.wanted.preonboard.post.domain.Post;

public record PostContentResponse(
        String title,
        String content,
        Long memberId,
        String memberNickname
) {
    public static PostContentResponse from(Post post) {
        return new PostContentResponse(
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getMember().getNickname()
        );
    }
}
