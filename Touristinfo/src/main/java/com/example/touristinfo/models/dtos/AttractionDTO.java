package com.example.touristinfo.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO {


    private Long id;


    private String name;

    private String city;


    private int price;


    private float longitude;

    private float latitude;
    private String category;
    private int duration;
    private float recommendedAge;
}
