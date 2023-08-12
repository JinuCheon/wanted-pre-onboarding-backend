package com.wanted.preonboard.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptEncoder {
    public String hashPassword(String raw) {
        return BCrypt.hashpw(raw, BCrypt.gensalt());
    }

    public boolean isMatch(String raw, String hashed) {
        return BCrypt.checkpw(raw, hashed);
    }
}