package com.example.touristinfo.controllers;

import com.example.touristinfo.models.ReservedTickets;
import com.example.touristinfo.services.AttrService;
import com.example.touristinfo.services.ResTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {

    private ResTicketService resTicketService;

    private AttrService attrService;

    @Autowired
    public TicketController(ResTicketService resTicketService, AttrService attrService) {
        this.resTicketService = resTicketService;
        this.attrService = attrService;
    }
    @PostMapping("/tickets/reserve")
    public String add(@ModelAttribute ReservedTickets ticket, @RequestParam Long attractionId) {
        if (resTicketService.validateTicket(ticket, attractionId)) {
            attrService.saveTicket(ticket, attractionId);
            return "redirect:/";
        } else {
            throw new RuntimeException("Validation failed!");
        }
    }

}
