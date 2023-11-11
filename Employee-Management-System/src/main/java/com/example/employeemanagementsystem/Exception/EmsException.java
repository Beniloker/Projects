package com.example.employeemanagementsystem.Exception;

import org.springframework.http.HttpStatus;

public class EmsException extends Exception {
    HttpStatus httpStatus;

    public EmsException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public EmsException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public EmsException(HttpStatus status){
        this.httpStatus = status;
    }
}
