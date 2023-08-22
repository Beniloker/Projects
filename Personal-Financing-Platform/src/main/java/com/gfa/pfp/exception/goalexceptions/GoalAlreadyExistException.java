package com.gfa.pfp.exception.goalexceptions;

import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PfpException;
import org.springframework.http.HttpStatus;

public class GoalAlreadyExistException extends PfpException {
  public GoalAlreadyExistException(String message) {
    super(message, HttpStatus.CONFLICT);
  }

  public GoalAlreadyExistException() {
    this(ExceptionMessage.GOAL_ALREADY_EXIST.getMessage());
  }
}
