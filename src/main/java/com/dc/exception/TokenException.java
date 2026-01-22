package com.dc.exception;

public class TokenException extends GenericException {
    public TokenException(String fieldName,String message) {
        super(fieldName,message);
    }
}
