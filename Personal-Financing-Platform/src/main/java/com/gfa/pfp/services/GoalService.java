package com.gfa.pfp.services;

import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.models.dto.GoalProgressResponseDTO;
import com.gfa.pfp.models.dto.MessageDTO;
import com.gfa.pfp.models.entities.finance.Goal;
import com.gfa.pfp.models.entities.user.User;
import java.util.List;

public interface GoalService {
  MessageDTO createNewGoal(Goal goal, String request) throws PfpException;

  MessageDTO deposit(Goal goal, Double deposit, String request) throws PfpException;

  MessageDTO withdrawal(Goal goal, Double withdrawal, String request) throws PfpException;

  Integer percentage(Goal goal, User user);

  GoalProgressResponseDTO checkProgress(Goal goal, String request) throws PfpException;

  List<GoalProgressResponseDTO> showAllGoal(String request) throws PfpException;

  MessageDTO deleteGoal(Goal goal, String request) throws PfpException;

  MessageDTO editGoal(String name, Goal goal, String request) throws PfpException;

  void checkName(String name) throws PfpException;

  void checkExist(String name, User user) throws PfpException;
}
