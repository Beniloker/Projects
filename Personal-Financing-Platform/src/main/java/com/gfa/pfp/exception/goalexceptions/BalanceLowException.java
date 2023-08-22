package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class BalanceLowException extends PfpException {
  public BalanceLowException(String message) {
    super(message, HttpStatus.CONFLICT);
  }

  public BalanceLowException() {
    this(ExceptionMessage.BALANCE_LOW.getMessage());
  }
}
