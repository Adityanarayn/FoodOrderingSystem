package com.fos.FoodOrderingSystem.model;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.*;

@Data
public class Restaurant {
    @NotBlank(message = "Restaurant name is required")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Rating must be positive")
    @DecimalMax(value = "5.0", message = "Rating cannot be more than 5")
    private double rating;

    @Min(value = 1, message = "Max orders must be at least 1")
    private int maxOrders;
//    private final String name;
//    private final double rating;
//    private final int maxOrders;
    private final Map<String, MenuItem> menu = new HashMap<>();
    private final Set<Integer> activeOrderIds = new HashSet<>();

    public Restaurant(String name, double rating, int maxOrders){
        this.name = name;
        this.rating = rating;
        this.maxOrders = maxOrders;
    }

    public boolean canAcceptOrder(Map<String, Integer> items){
        if(activeOrderIds.size() >= maxOrders) return false;
        // check and return for all the items present in the menu;
        return items.keySet().stream().allMatch(menu::containsKey);
    }

    public int calculateBill(Map<String, Integer> items){
        return items.entrySet().stream()
                .mapToInt(e -> menu.get(e.getKey())
                        .getPrice()*e.getValue()).sum();
    }
    public void addMenuItem(String name, int price){
        menu.putIfAbsent(name, new MenuItem(name, price));
    }

    public void updateMenuItemPrice(String name, int price){
        if(!menu.containsKey(name)){
            throw new NoSuchElementException("Menu item "+name+" Not Found !!");
        }
        menu.get(name).setPrice(price);

    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public int getMaxOrders() {
        return maxOrders;
    }

    public Map<String, MenuItem> getMenu() {
        return menu;
    }

    public Set<Integer> getActiveOrderIds() {
        return activeOrderIds;
    }
}
