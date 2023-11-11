package com.example.employeemanagementsystem.Exception;

import org.springframework.http.HttpStatus;

public class EmailIsMissingException extends EmsException {
    public EmailIsMissingException(String message) {
        super(message, HttpStatus.EXPECTATION_FAILED);
    }

    public EmailIsMissingException() {
        this(ExceptionMessage.EMAIL_MISSING.getMessage());
    }
}
