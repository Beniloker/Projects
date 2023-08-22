package com.example.auction.services;

import com.example.auction.models.Bid;
import com.example.auction.models.Item;
import com.example.auction.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

    private BidRepository bidRepository;

    private ItemService itemService;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, ItemService itemService) {

        this.bidRepository = bidRepository;
        this.itemService = itemService;
    }


    @Override
    public void saveBid(Bid bid) {

        //if (itemService.placeBid(bid.getItem().getId(), bid.getAmount())) {
            bidRepository.save(bid);
        //    System.out.println(bid.getBidder_name());
        //    System.out.println(bid.getAmount());
        //    System.out.println(bid.getItem());
        }

    //}
}
