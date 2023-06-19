package com.database.billingSystem.service;

import com.database.billingSystem.entity.Customer;
import com.database.billingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository repository;
    @Override
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> getCustomer() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Customer customer, String id) {
        Customer oldCustomer = repository.findById(id).orElse(null);
        oldCustomer.setCustomerId(customer.getCustomerId());
        oldCustomer.setCustomerName(customer.getCustomerName());
        oldCustomer.setPaymentMode(customer.getPaymentMode());
        repository.save(oldCustomer);
        return oldCustomer;
    }

    @Override
    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean customerExists(String id) {
        return repository.existsById(id);
    }
}
