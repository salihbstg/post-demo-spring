package com.example.demo.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private int status;
    private String message;
    private long timeStamp;
}
