package com.example.auction.controllers;


import com.example.auction.models.Bid;
import com.example.auction.models.Item;
import com.example.auction.models.dtos.BidDTO;
import com.example.auction.models.dtos.ItemDTO;
import com.example.auction.services.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuctionRestController {
    private final ItemService itemService;

    public AuctionRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/api/items")
    public Object itemsQuery() {
        List<Item> items = itemService.getAllItem();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setTitle(item.getName());
            itemDTO.setExpire_date(item.getExpireDate());
            List<BidDTO> bidDTOs = new ArrayList<>();
            for (Bid bid : item.getBids()) {
                BidDTO bidDto = new BidDTO();
                bidDto.setName(bid.getBidder_name());
                bidDto.setAmount(bid.getAmount());
                bidDTOs.add(bidDto);
            }
            itemDTO.setBids(bidDTOs);
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }
}
