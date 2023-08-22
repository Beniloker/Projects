package com.example.touristinfo.services;

import com.example.touristinfo.models.Attraction;
import com.example.touristinfo.models.ReservedTickets;

import java.util.List;

public interface ResTicketService {

    boolean validateTicket(ReservedTickets reservedTickets, Long attractionId);

}
