package com.example.employeemanagementsystem.Exception;

import org.springframework.http.HttpStatus;

public class PasswordIsMissingException extends EmsException {
    public PasswordIsMissingException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public PasswordIsMissingException() {
        this(ExceptionMessage.PASSWORD_MISSING.getMessage());
    }
}
