package com.gfa.pfp.repositories;

import com.gfa.pfp.models.entities.finance.Goal;
import com.gfa.pfp.models.entities.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
  Goal findByNameAndUser(String name, User user);

  boolean existsByNameAndUser(String name, User user);

  List<Goal> findAllByUser(User user);
}
