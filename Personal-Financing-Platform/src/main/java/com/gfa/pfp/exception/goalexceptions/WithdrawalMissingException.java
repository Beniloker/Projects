package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class WithdrawalMissingException extends PfpException {
  public WithdrawalMissingException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public WithdrawalMissingException() {
    this(ExceptionMessage.WITHDRAWAL_MISSING.getMessage());
  }
}