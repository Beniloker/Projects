package com.example.auction.repositories;

import com.example.auction.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    Item findItemByIdAndExpireDateAfter(long id , Date currentDate);
}
