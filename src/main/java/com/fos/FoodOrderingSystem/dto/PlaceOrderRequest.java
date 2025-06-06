package com.fos.FoodOrderingSystem.dto;

import com.fos.FoodOrderingSystem.model.enums.SelectionStrategyType;

import java.util.Map;

public class PlaceOrderRequest {

    private String userName;
    private Map<String, Integer> items;
    private SelectionStrategyType strategy;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public SelectionStrategyType getStrategy() {
        return strategy;
    }

    public void setStrategy(SelectionStrategyType strategy) {
        this.strategy = strategy;
    }
}
