package com.gfa.pfp.repositories;

import com.gfa.pfp.models.entities.finance.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);

  Optional<Category> findByIdAndUserId(Long id, Long userId);

  List<Category> findAllByUserId(Long userId);

    Optional<Category> findByUserIdAndName(Long userId, String name);

}