package com.dc.exception;

public class TokenExpiredExpection extends RuntimeException {
    public TokenExpiredExpection(String message) {
        super(message);
    }
}
