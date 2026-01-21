package com.dc.exception;

public class PatientException extends RuntimeException {
    private String fieldName;
    public PatientException(String message,String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
