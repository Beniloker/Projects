package com.example.employeemanagementsystem.Exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends EmsException {

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public UserAlreadyExistsException() {
        this(ExceptionMessage.USER_ALREADY_EXIST.getMessage());
    }
}