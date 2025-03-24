package com.example.RealTimeTicketingBackend.ticketing;

import com.example.RealTimeTicketingBackend.configuration.Configuration;
import com.example.RealTimeTicketingBackend.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final Configuration config;

    public Vendor(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        int id = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.addTickets(new Ticket(id++));
                Thread.sleep(config.getTicketReleaseRate()*1000); // Configurable rate for ticket release
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}