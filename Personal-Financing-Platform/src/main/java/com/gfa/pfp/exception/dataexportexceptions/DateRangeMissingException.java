package com.gfa.pfp.exception.dataexportexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class DateRangeMissingException extends PfpException {
  public DateRangeMissingException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public DateRangeMissingException() {
    this(ExceptionMessage.DATE_RANGE_MISSING.getMessage());
  }
}
