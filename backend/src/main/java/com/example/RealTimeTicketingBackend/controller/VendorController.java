package com.example.RealTimeTicketingBackend.controller;

import com.example.RealTimeTicketingBackend.model.VendorEntity;
import com.example.RealTimeTicketingBackend.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public VendorEntity addVendor(@RequestBody VendorEntity vendor){
        return vendorService.addVendor(vendor);
    }

    @GetMapping
    public List<VendorEntity> getAllVendors(){
        return vendorService.getAllVendors();
    }
}
