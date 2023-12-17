package com.ecm.ecommerce.Exception;

import org.springframework.http.HttpStatus;

public class EmailIsMissingException extends EcException {
    public EmailIsMissingException(String message) {
        super(message, HttpStatus.EXPECTATION_FAILED);
    }

    public EmailIsMissingException() {
        this(ExceptionMessage.EMAIL_MISSING.getMessage());
    }
}
