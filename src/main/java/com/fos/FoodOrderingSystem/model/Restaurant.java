package com.fos.FoodOrderingSystem.model;


import lombok.Data;

import java.util.*;

@Data
public class Restaurant {
    private final String name;
    private final double rating;
    private final int maxOrders;
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
        }else{
            menu.get(name).setPrice(price);
        }
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
