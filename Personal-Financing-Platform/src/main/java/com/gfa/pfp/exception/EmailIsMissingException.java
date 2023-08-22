package com.gfa.pfp.exception;

import org.springframework.http.HttpStatus;

public class EmailIsMissingException extends PfpException {
  public EmailIsMissingException(String message) {
    super(message, HttpStatus.EXPECTATION_FAILED);
  }

  public EmailIsMissingException() {
    this(ExceptionMessage.EMAIL_MISSING.getMessage());
  }
}