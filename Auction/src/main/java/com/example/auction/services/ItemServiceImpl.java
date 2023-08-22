package com.example.auction.services;

import com.example.auction.models.Item;
import com.example.auction.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    public ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.getReferenceById(id);
    }

    @Override
    public Item onRedirectById(Long id) {
        Item item = getById(id);
        itemRepository.save(item);
        return item;
    }

    @Override
    public int getHighestBid(Item item) {
       // System.out.println(item.getBids());
        int highestBid = item.getBids().stream().mapToInt(b -> b.getAmount()).max().getAsInt();

        return highestBid;
    }

    @Override
    public boolean placeBid(Long id, int bidAmount) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            int maxBid = item.getBids().stream().mapToInt(c -> c.getAmount()).max().getAsInt();

            if (bidAmount > maxBid) {
                return true;
            }
            return false;
        }

        return false;
    }

    //@Override
    //    public Integer findHighestBidOnItemById(Long id) {
    //        Item item = findById(id);
    //        if (item.getListOfBids().isEmpty()){
    //            return 0;
    //        }
    //        return item.getListOfBids().stream().reduce((i1, i2) -> i1.getAmount() > i2.getAmount() ? i1 : i2).get().getAmount();
    //    }



    @Override
    public boolean isExpired(LocalDateTime inputDate) {
        LocalDateTime today = LocalDateTime.now();
        if (today.isAfter(inputDate)) {
            return true;
        }
        return false;
    }

    @Override
    public Item getUnExpiredItem(long id, Date currentDate) {
        return itemRepository.findItemByIdAndExpireDateAfter(id, currentDate);
    }
}