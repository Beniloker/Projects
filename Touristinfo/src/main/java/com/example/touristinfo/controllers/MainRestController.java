package com.example.touristinfo.controllers;

import com.example.touristinfo.services.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

    private final AttrService attrService;


    @Autowired
    public MainRestController(AttrService attrService) {
        this.attrService = attrService;
    }

    @GetMapping("/api/popular")
    public Object popularAttraction() {
        return  attrService.getMostPopularAttraction();
    }

    @GetMapping("/api/budget")
    public Object cheapAttractionsByCategory() {
        return  attrService.getCheapAttractionsByCategory();

    }
}
