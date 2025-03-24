package com.example.RealTimeTicketingBackend.repository;

import com.example.RealTimeTicketingBackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository <Ticket,Integer> {
}

