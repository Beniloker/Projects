package com.example.employeemanagementsystem.Exception;

    public class MissingParameterException extends IllegalArgumentException {
        public MissingParameterException(String message) {
            super(message);
        }
}
