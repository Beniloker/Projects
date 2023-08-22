package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class TargetMissingException extends PfpException {
  public TargetMissingException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public TargetMissingException() {
    this(ExceptionMessage.TARGET_MISSING.getMessage());
  }
}
