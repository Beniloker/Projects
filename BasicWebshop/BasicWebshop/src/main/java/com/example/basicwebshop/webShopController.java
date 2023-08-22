package com.example.basicwebshop;

import com.example.basicwebshop.Model.ShopItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class webShopController {

        List<ShopItem> shopitemList = new ArrayList<>(){{
                add(new ShopItem("Tomato seeds", "Tomato seeds from the last summer harvest", 300.0, 30));
                add(new ShopItem("Tomato plant", "Tomato plant from our bio greenhouse", 1200.0, 0));
                add(new ShopItem("Eggplant plant", "Eggplant from our bio greenhouse", 1000.0, 7));
                add(new ShopItem("Japanese maple tree", "1-3 years Japanese maple tree from our garden, in a pot", 15000.0, 3));
                add(new ShopItem("Biointensive farming handbook", "100-page guide on how to have a beautiful garden yourself", 3000.0, 1));
        }};

        @RequestMapping(value = "/")
        public String showAll(Model model){
                model.addAttribute("shopitems", shopitemList);
                return "index";
        }

        @RequestMapping(path= "/only-available")
        public String showavailable(Model model){
                List<ShopItem> availableshopitemList = shopitemList.stream()
                        .filter(item -> item.getQuantityOfStock() > 0)
                        .collect(Collectors.toList());
                model.addAttribute("shopitems", availableshopitemList);
                return "index";
        }

        @RequestMapping(path= "/cheapest-first")
        public String showCheapestFirst(Model model){
                List<ShopItem> cheapestFirstShopitemList = shopitemList.stream()
                        .sorted(Comparator.comparingDouble(ShopItem::getPrice))
                        .collect(Collectors.toList());
                model.addAttribute("shopitems", cheapestFirstShopitemList);
                return "index";
        }
        @RequestMapping(path= "/contains-plant")
        public String containsPlant(Model model){
                List<ShopItem> containsPlantShopitemList = shopitemList.stream()
                        .filter(item -> item.getName().contains("plant") || item.getDescription().contains("plant"))
                        .collect(Collectors.toList());
                model.addAttribute("shopitems", containsPlantShopitemList);
                return "index";
        }
        @RequestMapping(path= "/average-stock")
        public String averageStock(Model model){
                OptionalDouble averageStock = shopitemList.stream()
                        .mapToDouble(item -> item.getQuantityOfStock())
                        .average();
                if (averageStock.isPresent()){
                        model.addAttribute("average", averageStock.getAsDouble());
                } else model.addAttribute("average", 0.0);

                return "averageStock";
        }
        @RequestMapping(path= "/most-expensive")
        public String mostExpensive(Model model){
                String mostExpensive = shopitemList.stream()
                        .filter(item -> item.getQuantityOfStock() > 0)
                        .max((item1, item2) -> item1.getPrice() > item2.getPrice() ? 1 : -1)
                        .get().getName()
                        .toString();
                model.addAttribute("mostExpensive", mostExpensive);
                return "MostExpensive";
        }

        @RequestMapping(path = "/search")
        public String search(Model model, @RequestParam(required = false, name = "search") String searching){
                List<ShopItem> searchedShopitemList = shopitemList.stream()
                        .filter(item -> item.getName().toUpperCase().contains(searching.toUpperCase()) || item.getDescription().toUpperCase().contains(searching.toUpperCase()))
                        .collect(Collectors.toList());
                        model.addAttribute("shopitems", searchedShopitemList);
                return "index";
        }
}
