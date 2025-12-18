 package com.example.demo.service;

import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import java.util.List;

public interface InvoiceService {
    Invoice uploadInvoice(Invoice invoice);
    List<Invoice> getInvoicesByUser(User user);
    Invoice getInvoiceById(Long id);
}
