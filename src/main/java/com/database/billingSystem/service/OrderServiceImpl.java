package com.database.billingSystem.service;

import com.database.billingSystem.entity.Order;
import com.database.billingSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repository;
    @Autowired
    ItemService itemService;
    @Override
    public Order createOrder(Order order) {
        order.setTotalPrice((order.getQuantity())*itemService.getItemById(order.getItemId()).getItemPrice());
        return repository.save(order);
    }

    @Override
    public List<Order> getOrder() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
            return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrder(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean orderExists(String id) {
        return repository.existsById(id);
    }
}
