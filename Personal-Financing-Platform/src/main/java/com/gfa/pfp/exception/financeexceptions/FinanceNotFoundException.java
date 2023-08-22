package com.gfa.pfp.exception.financeexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class FinanceNotFoundException extends PfpException {

  public FinanceNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public FinanceNotFoundException() {
    this(ExceptionMessage.FINANCE_NOT_EXIST.getMessage());
  }
}
