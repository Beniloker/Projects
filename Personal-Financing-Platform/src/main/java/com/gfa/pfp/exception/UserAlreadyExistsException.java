package com.gfa.pfp.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends PfpException {

  public UserAlreadyExistsException(String message) {
    super(message, HttpStatus.CONFLICT);
  }

  public UserAlreadyExistsException() {
    this(ExceptionMessage.USER_ALREADY_EXIST.getMessage());
  }
}