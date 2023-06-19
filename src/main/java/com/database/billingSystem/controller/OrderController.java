package com.database.billingSystem.controller;

import com.database.billingSystem.entity.Order;
import com.database.billingSystem.service.CustomerService;
import com.database.billingSystem.service.ItemService;
import com.database.billingSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    CustomerService customerService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;
    @PostMapping("/add")
    public Object createOrder(@RequestBody Order order){
        if(customerService.customerExists(order.getCustomerId()) && itemService.itemsExist(order.getItemId())){
            return orderService.createOrder(order);
        }
        return "Check itemId or customerId!!!";
    }
    @GetMapping("/get")
    public List<Order> getOrders(){
        return orderService.getOrder();
    }
    @GetMapping("/get/{id}")
    public Object getOrderById(@PathVariable String id){
        if (orderService.orderExists(id)){
            return orderService.getOrderById(id);
        }
        return "Order doesn't exist";
    }

}
