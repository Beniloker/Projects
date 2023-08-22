package com.example.touristinfo.services;

import com.example.touristinfo.models.Attraction;
import com.example.touristinfo.models.ReservedTickets;
import com.example.touristinfo.repositories.AttrRepository;
import com.example.touristinfo.repositories.ResTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResTicketImpl implements ResTicketService {
    private AttrRepository attrRepository;

    private ResTicketRepository resTicketRepository;

    @Autowired
    public ResTicketImpl(AttrRepository attrRepository, ResTicketRepository resTicketRepository) {
        this.attrRepository = attrRepository;
        this.resTicketRepository = resTicketRepository;
    }


    @Override
    public boolean validateTicket(ReservedTickets reservedTickets, Long attractionId) {
        if (reservedTickets.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid quantity!");
        }
        Attraction attraction = attrRepository.findById(attractionId).get();
        if (attraction.getCategory().equalsIgnoreCase("restaurant")) {
            throw new IllegalArgumentException("Restaurants don't need tickets!");
        }
        return true;
    }
}

