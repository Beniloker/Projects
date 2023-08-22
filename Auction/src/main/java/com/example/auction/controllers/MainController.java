package com.example.auction.controllers;

import com.example.auction.models.Bid;
import com.example.auction.models.Item;
import com.example.auction.services.BidService;
import com.example.auction.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {


    private ItemService itemService;
    private BidService bidService;

    @Autowired
    public MainController(ItemService itemService, BidService bidService) {

        this.itemService = itemService;
        this.bidService = bidService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Item> items = itemService.getAllItem();
        model.addAttribute("items", items);
        //  model.addAttribute("items", itemService.getAllItem());

        return "index";
    }

    @GetMapping("/item/{id}")
    public String itemInfo(@PathVariable Long id, Model model) {
        Item item = itemService.getUnExpiredItem(id, new Date());
        if (item == null) {
            model.addAttribute("error", "<strong>The auction is over! </strong>");
            item = itemService.getById(id);
        }

        model.addAttribute("item", item);


        int bidAmount = itemService.getHighestBid(item);


        model.addAttribute("highestBid", bidAmount);
        model.addAttribute("newBid", new Bid());
        return "bid_page";


        //  Item items = itemService.getById(id);
        //  if (items.getBids().isEmpty()) {
        //     model.addAttribute("highestBid", 0);
        // } else {
        //     model.addAttribute("highestBid", itemService.getHighestBid(items));

        //  }
        //  return "redirect:/";
    }

    @PostMapping("/item/{id}")
    public String saveBidToItem(@PathVariable(name = "id") long id,
                                RedirectAttributes redirectAttributes,
                                @ModelAttribute Bid bid) {
        Item item = itemService.getById(id);
        int highestBid = itemService.getHighestBid(item);

        if (bid.getAmount() <= highestBid) {
            redirectAttributes.addFlashAttribute("lowBid", "Your bid is below the highest bid");
            return "redirect:/item/{id}";
        }
        bid.setItem(item);
        item.addBid(bid);
        bidService.saveBid(bid);
        return "redirect:/item/{id}";
    }

}
