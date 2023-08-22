package com.example.auction.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "expire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireDate;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;


    public void addBid(Bid bid) {
        this.bids.add(bid);

    //    if (bids == null) {
      //      bids = new ArrayList<>();
      //  }
      //  bid.setItem(this);
      //  bids.add(bid);
    }
}
