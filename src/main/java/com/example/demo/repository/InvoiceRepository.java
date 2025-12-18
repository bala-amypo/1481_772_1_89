 package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Invoice;
import com.example.demo.model.User;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUploadedBy(User user);

    // Additional example query for amount filtering if needed
    List<Invoice> findByAmountGreaterThan(Double amount);
}
