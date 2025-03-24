package com.example.RealTimeTicketingBackend.service;

import com.example.RealTimeTicketingBackend.model.VendorEntity;
import com.example.RealTimeTicketingBackend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;


    public VendorEntity addVendor(VendorEntity vendor) {
        return vendorRepository.save(vendor);
    }

    public List<VendorEntity> getAllVendors() {
        return vendorRepository.findAll();
    }


}
