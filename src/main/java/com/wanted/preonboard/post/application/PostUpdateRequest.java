package com.wanted.preonboard.post.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUpdateRequest(
        @NotNull(message = "게시글 ID는 필수입니다.")
        Long postId,
        @NotBlank(message = "게시글 제목은 필수입니다.")
        String updatedTitle,
        @NotBlank(message = "게시글 내용은 필수입니다.")
        String updatedContent) {
}
