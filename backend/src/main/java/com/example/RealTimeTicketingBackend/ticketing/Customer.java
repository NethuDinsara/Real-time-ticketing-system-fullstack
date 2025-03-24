package com.example.RealTimeTicketingBackend.ticketing;

import com.example.RealTimeTicketingBackend.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class Customer implements Runnable {

    private final TicketPool ticketPool;
    private final Configuration config;

    public Customer(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.removeTicket();
                Thread.sleep(config.getCustomerRetrievalRate()*1000); // Configurable rate for ticket retrieval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


