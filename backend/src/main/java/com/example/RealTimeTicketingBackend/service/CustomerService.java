package com.example.RealTimeTicketingBackend.service;

import com.example.RealTimeTicketingBackend.model.CustomerEntity;
import com.example.RealTimeTicketingBackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity addCustomer(CustomerEntity customer){
        return customerRepository.save(customer);
    }
    public List<CustomerEntity> getAllCustomers(){
        return customerRepository.findAll();
    }


}