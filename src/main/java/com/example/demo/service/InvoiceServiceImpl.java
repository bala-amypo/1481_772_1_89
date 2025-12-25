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

    public InvoiceServiceImpl(InvoiceCategorizationEngine categorizationEngine) {
        this.categorizationEngine = categorizationEngine;
    }

    @Override
    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        Category category = categorizationEngine.categorize(invoice, rules);
        invoice.setCategory(category);
        // Save to DB if you have a repository
    }

    @Override
    public Invoice getInvoice(Long id) {
        // Implement fetching logic, e.g., from a repository
        // return invoiceRepository.findById(id).orElse(null);
        return null; // placeholder if no repository yet
    }
}
