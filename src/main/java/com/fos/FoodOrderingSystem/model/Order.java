package com.fos.FoodOrderingSystem.model;

import com.fos.FoodOrderingSystem.model.enums.OrderStatus;
import com.fos.FoodOrderingSystem.model.enums.SelectionStrategyType;

import java.util.Map;

public class Order {
    private final int id;
    private final String userName;
    private final Map<String, Integer> items;
    private final SelectionStrategyType strategy;
    private OrderStatus status;
    private Restaurant restaurant;

    public Order(int id, String userName,
                 Map<String, Integer> items,
                 SelectionStrategyType strategy){
        this.id = id ;
        this.userName = userName;
        this.strategy = strategy;
        this.items = items;
        this.status = OrderStatus.ACCEPTED;

    }

    public void markCompleted() {
        this.status = OrderStatus.COMPLETED;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public SelectionStrategyType getStrategy() {
        return strategy;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
