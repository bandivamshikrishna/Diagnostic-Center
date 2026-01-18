package com.dc.exception;

public class MedicalTestNotFoundException extends RuntimeException {
    public MedicalTestNotFoundException(String message) {
        super(message);
    }
}
