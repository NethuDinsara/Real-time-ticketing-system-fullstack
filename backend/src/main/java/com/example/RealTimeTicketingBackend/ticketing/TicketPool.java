package com.example.RealTimeTicketingBackend.ticketing;

import com.example.RealTimeTicketingBackend.configuration.Configuration;
import com.example.RealTimeTicketingBackend.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TicketPool {
    private final int maxTicketCapacity;
    private Configuration config;
    private final List<Ticket> tickets = new ArrayList<>();
    private final List<String> transactionLog = Collections.synchronizedList(new ArrayList<>());


    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }


    public synchronized void addTickets(Ticket ticket) {
        while (tickets.size() >= maxTicketCapacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting to add a ticket. Pool is full.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket.getId() + ". Available Tickets: " + tickets.size() + "/" + maxTicketCapacity);
        transactionLog.add("\nAdded ticket: " + ticket.getId() + " by " + Thread.currentThread().getName() + ". Available Tickets: " + tickets.size() + "/" + maxTicketCapacity);
        notifyAll();
    }

    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting to remove a ticket. Pool is empty.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.remove(0);
        System.out.println(Thread.currentThread().getName() + " removed ticket: " + ticket.getId() + ". Available Tickets: " + tickets.size() + "/" + maxTicketCapacity);
        transactionLog.add("\nRemoved ticket: " + ticket.getId() + " by " + Thread.currentThread().getName() + ". Available Tickets: " + tickets.size() + "/" + maxTicketCapacity);
        notifyAll();
        return ticket;
    }

    public int getAvailableTickets() {
        return tickets.size();
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public List<String> getTransactionLog() {
        return new ArrayList<>(transactionLog);
    }




}