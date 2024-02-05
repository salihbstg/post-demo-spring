package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionResponseResponseEntity(ApplicationException exc){
        ExceptionResponse error=new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionResponseResponseEntity(RuntimeException exc){
        ExceptionResponse error=new ExceptionResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Bad request!  "+exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
