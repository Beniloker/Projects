package com.gfa.pfp.repositories;

import com.gfa.pfp.models.entities.finance.Category;
import com.gfa.pfp.models.entities.finance.Limit;
import com.gfa.pfp.models.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Integer> {

    Optional<Limit> findByCategoryAndUser(Category category, User user);

    Limit findByCategoryIdAndUser(Long categoryId, User user);

    Optional<Limit> findByCategory_Name(String category);

    List<Limit> findAllByUser(User user);

}