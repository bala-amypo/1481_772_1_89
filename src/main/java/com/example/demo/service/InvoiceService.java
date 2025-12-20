 package com.example.demo.service;

import com.example.demo.entity.Invoice;

public interface InvoiceService {

    Invoice createInvoice(
            Invoice invoice,
            Long vendorId,
            Long userId
    );
}
