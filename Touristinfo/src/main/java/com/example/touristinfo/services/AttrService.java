package com.example.touristinfo.services;

import com.example.touristinfo.models.Attraction;
import com.example.touristinfo.models.ReservedTickets;
import com.example.touristinfo.models.dtos.AttractionDTO;

import java.util.List;

public interface AttrService {

    List<Attraction> getAllAttr();



   Attraction findById(Long id);

   void saveAttraction(Attraction attraction);

    void saveTicket(ReservedTickets ticket, Long attractionId);

    Attraction getMostPopularAttraction();


    List<Attraction> getCheapAttractionsByCategory();
}
