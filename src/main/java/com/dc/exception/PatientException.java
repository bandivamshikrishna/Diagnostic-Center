package com.dc.exception;

public class PatientException extends GenericException {
    public PatientException(String message,String fieldName) {
        super(fieldName,message);
    }
}
