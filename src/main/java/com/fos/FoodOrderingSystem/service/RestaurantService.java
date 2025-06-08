package com.fos.FoodOrderingSystem.service;


import com.fos.FoodOrderingSystem.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService {
    private final Map<String, Restaurant> restaurantMap = new HashMap<>();

    public void onboardRestaurant(Restaurant restaurant){
        String name = restaurant.getName();
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Restaurant name cannot be null or empty");
        }
        String restaurantNameInLowerCase = name.toLowerCase();
        if (restaurantMap.containsKey(restaurantNameInLowerCase)){
            throw new IllegalArgumentException("Restaurant already exists");
        }
        restaurantMap.put(restaurant.getName(), restaurant);
    }

    public void addMenuItem( String restaurantName, String itemName, int price ){
        Restaurant restaurant = getRestaurant(restaurantName);
        restaurant.addMenuItem(itemName.toLowerCase(), price);
    }

    public void updateMenuItem(String restaurantName, String itemName, int newPrice){
        Restaurant restaurant = getRestaurant(restaurantName.toLowerCase());
        restaurant.updateMenuItemPrice(itemName.toLowerCase(), newPrice);



    }

    public Restaurant getRestaurant(String restaurantName) {
        Restaurant r = restaurantMap.get(restaurantName.toLowerCase());
        if (r == null) throw new NoSuchElementException("Restaurant Not Found!!");
        return r;
    }

    public List<Restaurant> getAllRestaurants(){
        return new ArrayList<>(restaurantMap.values());
    }
}
