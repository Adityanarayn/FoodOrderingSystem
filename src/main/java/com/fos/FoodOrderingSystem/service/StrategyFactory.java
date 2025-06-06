package com.fos.FoodOrderingSystem.service;

import com.fos.FoodOrderingSystem.model.enums.SelectionStrategyType;
import com.fos.FoodOrderingSystem.strategy.HighestRatingStrategy;
import com.fos.FoodOrderingSystem.strategy.LowestCostStrategy;
import com.fos.FoodOrderingSystem.strategy.SelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.fos.FoodOrderingSystem.model.enums.SelectionStrategyType.HIGHEST_RATING;
import static com.fos.FoodOrderingSystem.model.enums.SelectionStrategyType.LOWEST_COST;
@Component
public class StrategyFactory {
    @Autowired private LowestCostStrategy lowestCostStrategy;
    @Autowired private HighestRatingStrategy highestRatingStrategy;

    public SelectionStrategy getStrategy(SelectionStrategyType type){
        switch (type){
            case LOWEST_COST: return lowestCostStrategy;
            case HIGHEST_RATING: return highestRatingStrategy;
            default: throw new IllegalArgumentException("Unknown strategy");
        }
    }
}
