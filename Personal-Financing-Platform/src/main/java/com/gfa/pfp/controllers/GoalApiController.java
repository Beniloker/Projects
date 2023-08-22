package com.gfa.pfp.controllers;

import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.models.dto.GoalProgressResponseDTO;
import com.gfa.pfp.models.dto.MessageDTO;
import com.gfa.pfp.models.entities.finance.Goal;
import com.gfa.pfp.services.GoalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goal")
@RequiredArgsConstructor
public class GoalApiController {

  private final GoalService goalService;

  @PostMapping("/create")
  ResponseEntity<MessageDTO> createNewGoal(@RequestBody Goal goal,
                                           @RequestHeader String authorization)
      throws PfpException {
    return ResponseEntity.ok().body(goalService.createNewGoal(goal, authorization));
  }

  @PostMapping("/deposit")
  ResponseEntity<MessageDTO> depositIntoGoal(@RequestBody Goal goal,
                                             @RequestHeader String authorization)
      throws PfpException {
    return ResponseEntity.ok().body(goalService.deposit(goal, goal.getDeposit(), authorization));
  }

  @PostMapping("/withdrawal")
  ResponseEntity<MessageDTO> withdrawalFromGoal(@RequestBody Goal goal,
                                                @RequestHeader String authorization)
      throws PfpException {
    return ResponseEntity.ok()
        .body(goalService.withdrawal(goal, goal.getWithdrawal(), authorization));
  }

  @GetMapping("/progress")
  ResponseEntity<GoalProgressResponseDTO> progressOfGoal(@RequestBody Goal goal,
                                                         @RequestHeader String authorization)
      throws PfpException {
    return ResponseEntity.ok().body(goalService.checkProgress(goal, authorization));
  }

  @GetMapping("/goals")
  ResponseEntity<List<GoalProgressResponseDTO>> showAllGoals(@RequestHeader String authorization)
      throws PfpException {
    return ResponseEntity.ok().body(goalService.showAllGoal(authorization));
  }

  @DeleteMapping("/delete_goal")
  ResponseEntity<MessageDTO> deleteGoal(@RequestBody Goal goal, @RequestHeader String authorization)
      throws PfpException {
    return ResponseEntity.ok().body(goalService.deleteGoal(goal, authorization));
  }

  @PatchMapping("/edit_goal")
  ResponseEntity<MessageDTO> editGoal(@RequestParam String name, @RequestBody(required = false) Goal goal,
                                      @RequestHeader String authorization) throws PfpException {
    return ResponseEntity.ok().body(goalService.editGoal(name, goal, authorization));
  }
}
