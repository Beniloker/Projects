package com.example.touristinfo.services;

import com.example.touristinfo.models.Attraction;
import com.example.touristinfo.models.ReservedTickets;
import com.example.touristinfo.models.dtos.AttractionDTO;
import com.example.touristinfo.repositories.AttrRepository;
import com.example.touristinfo.repositories.ResTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    private AttrRepository attrRepository;

    private ResTicketRepository resTicketRepository;

    @Autowired
    public AttrServiceImpl(AttrRepository attrRepository, ResTicketRepository resTicketRepository) {
        this.attrRepository = attrRepository;
        this.resTicketRepository = resTicketRepository;
    }

    @Override
    public List<Attraction> getAllAttr() {
        return attrRepository.findAll();
    }


    @Override
    public Attraction findById(Long id) {
        return attrRepository.findById(id).get();
    }

    @Override
    public void saveAttraction(Attraction attraction) {
        attrRepository.save(attraction);

    }

    @Override
    public Attraction getMostPopularAttraction() {

        Integer max = 0;
        Attraction resultItem = new Attraction();
        for (Attraction item : attrRepository.findAll()) {

            Integer itemPopular = resTicketRepository.countReservedTicketsByAttraction_Id(item.getId());
            if (itemPopular > max) {
                max = itemPopular;
                resultItem = item;
            }

        }

        return resultItem;
    }
/*        List<Attraction> attractions = attrRepository.findAll();
        Attraction mostPopular = new Attraction();
        int maxTicketCount = 0;
        for (Attraction attraction : attractions) {
            int ticketCount = 0;
            ticketCount = attraction.getReservedTickets().stream()
                    .mapToInt(ReservedTickets::getQuantity)
                    .sum();
            if (ticketCount > maxTicketCount) {
                maxTicketCount = ticketCount;
                mostPopular = attraction;
            }
        }
        return mostPopular;
    }*/


    @Override
    public void saveTicket(ReservedTickets ticket, Long attractionId) {
        Attraction attraction = attrRepository.findById(attractionId).get();
        attraction.addTicket(ticket);
        attrRepository.save(attraction);
    }


    @Override
    public List<Attraction> getCheapAttractionsByCategory() {
        List<Attraction> cheapestRestaurants = attrRepository.findByCategoryOrderByPriceAsc(
                "restaurant");
        List<Attraction> cheapestParks = attrRepository.findByCategoryOrderByPriceAsc("park");
        List<Attraction> cheapestMuseums = attrRepository.findByCategoryOrderByPriceAsc("museum");
        return List.of(cheapestRestaurants.get(0), cheapestParks.get(0), cheapestMuseums.get(0));
    }
}





