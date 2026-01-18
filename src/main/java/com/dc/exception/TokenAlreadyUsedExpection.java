package com.dc.exception;

public class TokenAlreadyUsedExpection extends RuntimeException {
    public TokenAlreadyUsedExpection(String message) {
        super(message);
    }
}
