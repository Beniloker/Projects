package com.ecm.ecommerce.Exception ;

import org.springframework.http.HttpStatus;

public class EcException extends Exception {
    HttpStatus httpStatus;

    public EcException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public EcException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public EcException(HttpStatus status){
        this.httpStatus = status;
    }
}
