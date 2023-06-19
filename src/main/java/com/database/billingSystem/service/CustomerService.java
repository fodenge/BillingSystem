package com.database.billingSystem.service;

import com.database.billingSystem.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public List<Customer> getCustomer();
    public Customer getCustomerById(String id);
    public Customer updateCustomer(Customer customer,String id);
    public void deleteCustomer(String id);
    public boolean customerExists(String id);
}
