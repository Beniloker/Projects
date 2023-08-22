package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class DepositMissingException extends PfpException {
  public DepositMissingException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public DepositMissingException() {
    this(ExceptionMessage.DEPOSIT_MISSING.getMessage());
  }
}
