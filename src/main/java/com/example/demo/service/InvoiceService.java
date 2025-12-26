 package com.example.demo.service;

import com.example.demo.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice);

    Invoice categorizeInvoice(Invoice invoice);

    Invoice getInvoiceById(Long id);

    List<Invoice> getInvoicesByUser(Long userId);
}
