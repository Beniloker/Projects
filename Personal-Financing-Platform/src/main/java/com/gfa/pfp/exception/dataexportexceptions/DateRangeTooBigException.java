package com.gfa.pfp.exception.dataexportexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class DateRangeTooBigException extends PfpException {
  public DateRangeTooBigException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public DateRangeTooBigException() {
    this(ExceptionMessage.DATE_RANGE_TOO_BIG.getMessage());
  }
}
