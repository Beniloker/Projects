package com.gfa.pfp.exception;

public class MissingParameterException extends IllegalArgumentException {
  public MissingParameterException(String message) {
    super(message);
  }
}
