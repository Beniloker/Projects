package com.example.auction.services;

import com.example.auction.models.Item;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ItemService {

    List<Item> getAllItem();

    Item getById(Long id);

    Item onRedirectById(Long id);

    int getHighestBid(Item item);

    boolean placeBid(Long id, int bidAmount);

    Item getUnExpiredItem(long id, Date currentDate);

    boolean isExpired(LocalDateTime inputDate);
}
