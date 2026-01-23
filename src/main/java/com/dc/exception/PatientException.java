package com.dc.exception;

public class PatientException extends GenericException {
    public PatientException(String fieldName,String message) {
        super(fieldName,message);
    }
}
