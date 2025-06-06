package com.fos.FoodOrderingSystem.strategy;

import com.fos.FoodOrderingSystem.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Restaurant select(List<Restaurant> candidates, Map<String, Integer> items) {
        return candidates.stream()
                .min(Comparator.comparingInt(r -> r.calculateBill(items)))
                .orElse(null);
    }

}
