package com.dc.exception;

public class MedicalTestAlreadyExistsException extends RuntimeException {
    public MedicalTestAlreadyExistsException(String message) {
        super(message);
    }
}
