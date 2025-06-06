package com.fos.FoodOrderingSystem.service;

import com.fos.FoodOrderingSystem.model.Order;
import com.fos.FoodOrderingSystem.model.Restaurant;
import com.fos.FoodOrderingSystem.model.enums.OrderStatus;
import com.fos.FoodOrderingSystem.model.enums.SelectionStrategyType;
import com.fos.FoodOrderingSystem.strategy.SelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Map<Integer, Order> orders = new HashMap<>();
    private int orderIdCounter = 1;

    @Autowired private StrategyFactory strategyFactory;
    @Autowired private RestaurantService restaurantService;

    public Order placeOrder(String userName, Map<String, Integer> items, SelectionStrategyType strategyType) {
        List<Restaurant> all = restaurantService.getAllRestaurants();
        List<Restaurant> eligible = all.stream()
                .filter(r -> r.canAcceptOrder(items))
                .collect(Collectors.toList());

        if (eligible.isEmpty()){
            System.out.println("No restaurant can fulfill this order");
            return null;
        }

        SelectionStrategy strategy = strategyFactory.getStrategy(strategyType);
        Restaurant assigned = strategy.select(eligible, items);
        if (assigned == null){
            System.out.println("Strategy could not assign a restaurant");
            return null;
        }

        Order order = new Order(orderIdCounter++, userName, items, strategyType);
        order.setRestaurant(assigned);
        orders.put(order.getId(), order);
        assigned.getActiveOrderIds().add(order.getId());

        return order;
    }

    public boolean markOrderCompleted(int orderId){
        Order order = orders.get(orderId);
        if(order == null ) return false;
        if(order.getStatus() != OrderStatus.ACCEPTED) return false;

        order.markCompleted();
        order.getRestaurant().getActiveOrderIds().remove(order.getId());
        return true;
    }

    public Order getOrder(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }

}
