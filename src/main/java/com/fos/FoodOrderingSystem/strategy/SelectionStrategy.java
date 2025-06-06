package com.fos.FoodOrderingSystem.strategy;

import com.fos.FoodOrderingSystem.model.Restaurant;

import java.util.List;
import java.util.Map;

public interface SelectionStrategy {
    Restaurant select(List<Restaurant> candidates, Map<String, Integer> items);
}
