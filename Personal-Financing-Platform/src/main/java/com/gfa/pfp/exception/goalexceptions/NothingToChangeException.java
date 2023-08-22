package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class NothingToChangeException extends PfpException {
  public NothingToChangeException(String message) {
    super(message, HttpStatus.EXPECTATION_FAILED);
  }

  public NothingToChangeException() {
    this(ExceptionMessage.NOTHING_TO_CHANGE.getMessage());
  }
}
