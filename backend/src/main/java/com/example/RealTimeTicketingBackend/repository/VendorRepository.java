package com.example.RealTimeTicketingBackend.repository;

import com.example.RealTimeTicketingBackend.model.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorEntity,Integer> {
}

