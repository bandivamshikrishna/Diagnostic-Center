package com.dc.exception;

public class VendorException extends GenericException {
    public VendorException(String fieldName,String message) {
        super(fieldName,message);
    }
}
