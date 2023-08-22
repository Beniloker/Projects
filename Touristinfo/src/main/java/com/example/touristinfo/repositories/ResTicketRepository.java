package com.example.touristinfo.repositories;

import com.example.touristinfo.models.ReservedTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResTicketRepository extends JpaRepository< ReservedTickets, Long> {

    Integer countReservedTicketsByAttraction_Id(Long id);
}
