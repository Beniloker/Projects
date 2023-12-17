package com.ecm.ecommerce.Exception;

    public class MissingParameterException extends IllegalArgumentException {
        public MissingParameterException(String message) {
            super(message);
        }
}
