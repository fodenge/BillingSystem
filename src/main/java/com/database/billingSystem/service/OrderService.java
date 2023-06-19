package com.database.billingSystem.service;

import com.database.billingSystem.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Order createOrder(Order order);
    public List<Order> getOrder();
    public Order getOrderById(String id);
    public void deleteOrder(String id);
    public boolean orderExists(String id);
}
