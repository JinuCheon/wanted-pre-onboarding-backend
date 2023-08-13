package com.wanted.preonboard.member.presentation;

import com.wanted.preonboard.auth.AuthService;
import com.wanted.preonboard.member.application.MemberService;
import com.wanted.preonboard.member.dto.request.MemberSignInRequest;
import com.wanted.preonboard.member.dto.request.MemberSignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberSignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody @Valid MemberSignInRequest request) {
        final Long memberId = memberService.signIn(request);
        return ResponseEntity.ok(authService.createToken(memberId));
    }

}
