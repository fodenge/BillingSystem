package com.database.billingSystem.controller;

import com.database.billingSystem.customHandler.CustomerNotFound;
import com.database.billingSystem.entity.Customer;
import com.database.billingSystem.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public Customer create(@RequestBody Customer customer){
        logger.info("CustomerId {} added",customer.getCustomerId());
        return customerService.createCustomer(customer);
    }
    @GetMapping("/get")
    public List<Customer> getAll(){
        logger.info("Customers retrieved");
        return customerService.getCustomer();
    }
    @GetMapping("/get/{id}")
    public Object getById(@PathVariable String id){
        try{
            if(customerService.customerExists(id)){
                logger.info("CustomerId {} retrieved",id);
                return customerService.getCustomerById(id);
            }
            throw new CustomerNotFound();
        }
        catch (CustomerNotFound e){
            logger.error("Customer doesn't exist");
        }
        return "Customer doesn't exist";
    }
    @PutMapping("/update/{id}")
    public Object updateCustomer(@RequestBody Customer customer,@PathVariable String id){
        try{
            if (customerService.customerExists(id)){
                logger.info("CustomerId {} updated",id);
                return customerService.updateCustomer(customer, id);
            }
            throw new CustomerNotFound();
        }
        catch (CustomerNotFound e){
            logger.error("Customer doesn't exist");
        }
        return "Customer doesn't exist";
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        try {
            if (customerService.customerExists(id)){
                logger.warn("CustomerId {} deleted!!",id);
                customerService.deleteCustomer(id);
                return new ResponseEntity<>("Customer removed!!!", HttpStatus.OK);
            }
            throw new CustomerNotFound();
        }
        catch (CustomerNotFound e){
            logger.error("Customer doesn't exist");
        }
        return new ResponseEntity<>("Customer not found!!!", HttpStatus.NOT_FOUND);
    }
}
