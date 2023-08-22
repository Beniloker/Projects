package com.example.basicwebshop.Model;

import lombok.Getter;

@Getter
public class ShopItem {
    String name;
    String description;
    double price;
    int quantityOfStock;

    public ShopItem(String name, String description, double price, int quantityOfStock){
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityOfStock = quantityOfStock;
    }
}
