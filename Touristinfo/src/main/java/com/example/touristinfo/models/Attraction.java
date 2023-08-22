package com.example.touristinfo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "attractions")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "attr_name")
    private String attrName;

    private String city;

    private String category;

    private int price;

    private float longitude;

    private float latitude;

    @Column(name = "recommended_age")
    private float recommendedAge;

    private int duration;

    @JsonIgnore
    @OneToMany(mappedBy = "attraction",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservedTickets> reservedTickets;


        public void addReservedTicket(ReservedTickets reservedTickets){
            this.reservedTickets.add(reservedTickets);
        }

    public void addTicket(ReservedTickets ticket){
        if (reservedTickets == null) {
            reservedTickets = new ArrayList<>();
        }
        ticket.setAttraction(this);
       reservedTickets.add(ticket);
    }
}
