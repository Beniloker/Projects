package com.ecm.ecommerce.Exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends EcException {

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException() {
        this(ExceptionMessage.UNAUTHORIZED.getMessage());
    }

}
