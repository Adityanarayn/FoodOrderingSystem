package com.fos.FoodOrderingSystem.controller;

import com.fos.FoodOrderingSystem.dto.PlaceOrderRequest;
import com.fos.FoodOrderingSystem.model.Order;
import com.fos.FoodOrderingSystem.model.Restaurant;
import com.fos.FoodOrderingSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestBody PlaceOrderRequest request){
        Order order = orderService.placeOrder(request.getUserName(), request.getItems(), request.getStrategy());
        if(order == null) return "Order could not be placed";
        return "Order placed with Order ID: "+ order.getId()+" assigned to: "+order.getRestaurant().getName();

    }

    @PutMapping("/{id}/complete")
    public String completeOrder(@PathVariable int id){
        boolean success = orderService.markOrderCompleted(id);
        return success ? "Order marked as completed" : "Order not found or already completed";
    }



}
