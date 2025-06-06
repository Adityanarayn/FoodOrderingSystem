package com.fos.FoodOrderingSystem.strategy;

import com.fos.FoodOrderingSystem.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Restaurant select(List<Restaurant> candidates, Map<String, Integer> items){
        return candidates.stream()
                .max(Comparator.comparingDouble(Restaurant::getRating))
                .orElse(null);
    }
}
