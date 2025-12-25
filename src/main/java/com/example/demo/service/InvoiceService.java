 package com.example.demo.service;

import com.example.demo.model.Invoice;

public interface InvoiceService {

    Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice);

    Invoice categorizeInvoice(Long invoiceId);

    Invoice getInvoice(Long invoiceId);
}
