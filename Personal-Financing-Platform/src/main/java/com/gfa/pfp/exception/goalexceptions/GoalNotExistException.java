package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class GoalNotExistException extends PfpException {
  public GoalNotExistException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public GoalNotExistException() {
    this(ExceptionMessage.GOAL_NOT_EXIST.getMessage());
  }
}
