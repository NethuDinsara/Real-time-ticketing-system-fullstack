package com.example.RealTimeTicketingBackend.repository;

import com.example.RealTimeTicketingBackend.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
