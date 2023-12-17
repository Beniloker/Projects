package com.ecm.ecommerce.Exception;

import org.springframework.http.HttpStatus;

public class PasswordIsMissingException extends EcException {
    public PasswordIsMissingException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public PasswordIsMissingException() {
        this(ExceptionMessage.PASSWORD_MISSING.getMessage());
    }
}
