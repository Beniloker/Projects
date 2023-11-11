package com.example.employeemanagementsystem.Exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends EmsException {

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException() {
        this(ExceptionMessage.UNAUTHORIZED.getMessage());
    }

}
