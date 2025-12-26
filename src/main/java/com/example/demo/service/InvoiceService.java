 package com.example.demo.service;

import com.example.demo.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    Invoice updateInvoice(Long id, Invoice invoice);
    void deleteInvoice(Long id);
    Invoice categorizeInvoice(Invoice invoice);
}
