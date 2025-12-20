 package com.example.demo.repository;

import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // REQUIRED by test
    List<Invoice> findByUploadedBy(User user);

    // REQUIRED by test (HQL style naming)
    List<Invoice> findByAmountGreaterThanHql(Double amount);
}
