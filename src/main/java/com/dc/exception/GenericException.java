package com.dc.exception;

public class GenericException extends RuntimeException {
    private String fieldName;
    public GenericException(String fieldName,String message) {
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
