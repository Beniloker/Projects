package com.gfa.pfp.exception.financeexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class FinanceErrorException extends PfpException {

  public FinanceErrorException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public FinanceErrorException() {
    this(ExceptionMessage.FINANCE_ERROR.getMessage());
  }
}
