package com.example.touristinfo.repositories;

import com.example.touristinfo.models.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttrRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByCategoryOrderByPriceAsc(String category);
}
