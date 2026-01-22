package com.dc.exception;

public class MedicalTestException extends GenericException {
    public MedicalTestException(String fieldName,String message) {
        super(fieldName,message);
    }
}
