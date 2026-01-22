package com.dc.exception;

public class UserException extends GenericException {
    public UserException(String fieldName,String message) {
        super(fieldName,message);
    }
}
