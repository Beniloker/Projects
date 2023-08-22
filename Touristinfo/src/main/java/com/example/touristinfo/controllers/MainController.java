package com.example.touristinfo.controllers;

import com.example.touristinfo.models.Attraction;
import com.example.touristinfo.models.ReservedTickets;
import com.example.touristinfo.services.AttrService;
import com.example.touristinfo.services.ResTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class MainController {

    private final AttrService attrService;

    private final ResTicketService resTicketService;

    @Autowired
    public MainController(AttrService attrService, ResTicketService resTicketService) {
        this.attrService = attrService;
        this.resTicketService = resTicketService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("listOfAttraction", attrService.getAllAttr());
        Attraction attraction = new Attraction();
        model.addAttribute("newAttraction", attraction);
        ReservedTickets ticket = new ReservedTickets();
        model.addAttribute("newTicket", ticket);
        model.addAttribute("attractions", attrService.getAllAttr());
        return "index";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute Attraction attraction) {
        attrService.saveAttraction(attraction);
        model.addAttribute("attractions", attrService.getAllAttr());
        return "redirect:/";
    }

@GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id ){
        Attraction attraction = attrService.findById(id);
        model.addAttribute("editAttraction", attraction);
        model.addAttribute("attractions", attrService.getAllAttr());
        return "edit";

}
@PostMapping("/edit/{id}")
public String editPost(Model model, @ModelAttribute ("attraction") Attraction attraction ){
      attrService.saveAttraction(attraction);
      model.addAttribute("attraction", attrService.getAllAttr());

      return "redirect:/";

}

}
