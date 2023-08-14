package com.wanted.preonboard.post.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUpdateRequest(
        @NotBlank(message = "게시글 제목은 필수입니다.")
        String updatedTitle,
        @NotBlank(message = "게시글 내용은 필수입니다.")
        String updatedContent) {
}
