 package com.example.demo.service;

import com.example.demo.entity.Invoice;
import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id);
    List<Invoice> getAllInvoices();
    Invoice updateInvoice(Long id, Invoice invoice);
    void deleteInvoice(Long id);
}
