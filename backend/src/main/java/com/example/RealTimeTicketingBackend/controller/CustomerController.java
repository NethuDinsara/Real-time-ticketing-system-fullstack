package com.example.RealTimeTicketingBackend.controller;

import com.example.RealTimeTicketingBackend.model.CustomerEntity;
import com.example.RealTimeTicketingBackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerEntity addCustomer(@RequestBody CustomerEntity customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<CustomerEntity> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody CustomerEntity customer){
        System.out.println("Deleting customer: " + customer);
    }
}