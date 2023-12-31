package com.wanted.preonboard.member.presentation;

import com.wanted.preonboard.auth.Auth;
import com.wanted.preonboard.auth.AuthService;
import com.wanted.preonboard.auth.UserContext;
import com.wanted.preonboard.member.application.MemberService;
import com.wanted.preonboard.member.dto.request.MemberSignInRequest;
import com.wanted.preonboard.member.dto.request.MemberSignUpRequest;
import com.wanted.preonboard.member.dto.response.SignInResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "맴버 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberSignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid MemberSignInRequest request) {
        final Long memberId = memberService.signIn(request);
        return ResponseEntity.ok(authService.createToken(memberId));
    }

}
