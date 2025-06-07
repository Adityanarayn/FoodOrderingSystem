package com.fos.FoodOrderingSystem.controller;


import com.fos.FoodOrderingSystem.model.Restaurant;
import com.fos.FoodOrderingSystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public String onBoardRestaurant(@RequestBody Restaurant restaurant){
        restaurantService.onboardRestaurant(restaurant);
        return "Restaurant Onboarded: "+ restaurant.getName();
    }

    @PutMapping("/{name}/menu/add")
    public String addMenuItem(
            @PathVariable String name,
            @RequestParam String itemName,
            @RequestParam int price) {
        restaurantService.addMenuItem(name, itemName, price);
        return "Item added to: "+name;
    }

    @PutMapping("/{name}/menu/update")
    public String updateMenuItem(
            @PathVariable String name,
            @RequestParam String itemName,
            @RequestParam int price) {
        restaurantService.updateMenuItem(name, itemName, price);
        return "Item price has been updated for: "+ name +" Restaurant";

    }

    @GetMapping("/{name}/menu")
    public Map<String, Integer> getMenu(@PathVariable String name) {
        Restaurant restaurant = restaurantService.getRestaurant(name);

        // Convert Map<String, MenuItem> → Map<String, Integer> (name → price)
        Map<String, Integer> menu = new HashMap<>();
        restaurant.getMenu().forEach((itemName, menuItem) ->
                menu.put(itemName, menuItem.getPrice())
        );

        return menu;
    }
}
