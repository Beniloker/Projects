package com.gfa.pfp.exception.dataexportexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class IOException extends PfpException {
  public IOException(String message) {
    super(message, HttpStatus.EXPECTATION_FAILED);
  }

  public IOException() {
    this(ExceptionMessage.CAN_NOT_WRITE_FILE.getMessage());
  }
}
