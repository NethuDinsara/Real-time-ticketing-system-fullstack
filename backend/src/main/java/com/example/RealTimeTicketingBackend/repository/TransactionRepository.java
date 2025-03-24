package com.example.RealTimeTicketingBackend.repository;

import com.example.RealTimeTicketingBackend.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
