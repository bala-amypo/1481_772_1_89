 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.model.Category;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceCategorizationEngine categorizationEngine;

    // Constructor injection
    public InvoiceServiceImpl(InvoiceCategorizationEngine categorizationEngine) {
        this.categorizationEngine = categorizationEngine;
    }

    @Override
    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        // Use InvoiceCategorizationEngine to find category
        Category category = categorizationEngine.categorize(invoice, rules);

        // Set the category to the invoice
        invoice.setCategory(category);

        // You can add logic to save the invoice if you have a repository
        // e.g., invoiceRepository.save(invoice);
    }
}
