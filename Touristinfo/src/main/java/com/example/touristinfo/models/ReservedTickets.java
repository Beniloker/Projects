package com.example.touristinfo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "reserved_tickets")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservedTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reserve_name")
    private String reserveName;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "attraction")

    private Attraction attraction;


}
