package com.wanted.preonboard.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberSignInRequest(
        @NotBlank(message = "이메일은 필수입니다.")
        String email,
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password) {
}
