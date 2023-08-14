package com.wanted.preonboard.common;

import com.wanted.preonboard.member.api.SignInMemberApi;
import com.wanted.preonboard.member.api.SignUpMemberApi;

public class Scenario {

    public static SignUpMemberApi signUpMember() {
        return new SignUpMemberApi();
    }

    public static SignInMemberApi signInMember() {
        return new SignInMemberApi();
    }
}
