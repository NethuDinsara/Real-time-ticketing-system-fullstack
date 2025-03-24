package com.example.RealTimeTicketingBackend.controller;

import com.example.RealTimeTicketingBackend.configuration.Configuration;
import com.example.RealTimeTicketingBackend.model.CustomerEntity;
import com.example.RealTimeTicketingBackend.model.VendorEntity;
import com.example.RealTimeTicketingBackend.repository.CustomerRepository;
import com.example.RealTimeTicketingBackend.repository.VendorRepository;
import com.example.RealTimeTicketingBackend.configuration.ConfigurationService;
import com.example.RealTimeTicketingBackend.ticketing.Customer;
import com.example.RealTimeTicketingBackend.ticketing.TicketPool;
import com.example.RealTimeTicketingBackend.ticketing.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StartController {

    private TicketPool ticketPool;
    private final List<Thread> threadList = new ArrayList<>();
    private boolean running = false;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ConfigurationService configurationService; // Use the service instead

    @GetMapping("/api/start")
    public synchronized String startThreads() {
        // Check if there are vendors and customers in the system
        long vendorCount = vendorRepository.count();
        long customerCount = customerRepository.count();

        if (vendorCount == 0) {
            return "Please log in as a vendor before starting the program.";
        }

        if (customerCount == 0) {
            return "Please log in as a customer before starting the program.";
        }

        if (running) {
            return "System is already running";
        }

        // Initialize ticket pool with config from service
        Configuration config = configurationService.getConfig();
        ticketPool = new TicketPool(config.getMaxTicketCapacity());

        running = true;
        List<VendorEntity> vendors = vendorRepository.findAll();
        List<CustomerEntity> customers = customerRepository.findAll();

        for (VendorEntity vendor : vendors) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, config));
            vendorThread.setName(vendor.getFirstName() + " " + vendor.getLastName());
            threadList.add(vendorThread);
            vendorThread.start();
        }

        for (CustomerEntity customer : customers) {
            Thread customerThread = new Thread(new Customer(ticketPool, config));
            customerThread.setName(customer.getFirstName() + " " + customer.getLastName());
            threadList.add(customerThread);
            customerThread.start();
        }

        return "System started with " + vendorCount + " vendors and " + customerCount + " customers.";
    }


    @GetMapping("/api/stop")
    public synchronized String stopThreads() {
        if (!running) {
            return "System is not running";
        }

        running = false;
        for (Thread thread : threadList) {
            thread.interrupt();
        }
        threadList.clear();

        StringBuilder logOutput = new StringBuilder("System stopped. Transaction log:\n");
        for (String log : ticketPool.getTransactionLog()) {
            logOutput.append(log).append("\n");
        }

        return logOutput.toString();
    }

    @GetMapping("/api/status")
    public synchronized String getStatus() {
        if (!running) {
            return "System is not running. No status available.";
        }

        StringBuilder status = new StringBuilder("Current Pool Status:\n");
        status.append("Available Tickets: ").append(ticketPool.getAvailableTickets()).append("\n");
        status.append("Max Capacity: ").append(ticketPool.getMaxTicketCapacity()).append("\n\n");
        status.append("Transaction Log:\n");
        for (String log : ticketPool.getTransactionLog()) {
            status.append(log).append("\n");
        }
        return status.toString();
    }
}
