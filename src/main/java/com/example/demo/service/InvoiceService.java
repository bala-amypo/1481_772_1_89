 package com.example.demo.service;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;

import java.util.List;

public interface InvoiceService {

    void categorizeInvoice(Invoice invoice, List<Rule> rules);

    Invoice getInvoice(Long id); // Make sure this exists
}
