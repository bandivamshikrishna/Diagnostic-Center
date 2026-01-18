package com.dc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
       e.getBindingResult().getFieldErrors().forEach(
               error->errors.put(error.getField(),error.getDefaultMessage()));
       return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleVendorNotFoundException(VendorNotFoundException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("Vendor", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(VendorAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleVendorAlreadyExistsException(VendorAlreadyExistsException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("email", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleVendorAlreadyExistsException(PhoneNumberAlreadyExistsException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("phoneNumber", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MedicalTestAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleMedicalTestAlreadyExistsException(MedicalTestAlreadyExistsException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("testName", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MedicalTestNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleMedicalTestNotFoundException(MedicalTestNotFoundException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("Medical Test", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String,String>> handleNoResourceFoundException(NoResourceFoundException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("Invalid URL", e.getResourcePath());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("email", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("createdByUserID", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleRoleNotFoundException(RoleNotFoundException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("roleID", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleTokenNotFoundException(TokenNotFoundException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("token", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(TokenAlreadyUsedExpection.class)
    public ResponseEntity<Map<String,String>> handleTokenAlreadyUsedExpection(TokenAlreadyUsedExpection e){
        Map<String,String> errors = new HashMap<>();
        errors.put("token", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(TokenExpiredExpection.class)
    public ResponseEntity<Map<String,String>> handleTokenExpiredExpection(TokenExpiredExpection e){
        Map<String,String> errors = new HashMap<>();
        errors.put("token", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Map<String,String>> handleLockedException(LockedException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.LOCKED).body(errors);
    }

    @ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<Map<String,String>> handleInvalidJWTException(InvalidJWTException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }



}
