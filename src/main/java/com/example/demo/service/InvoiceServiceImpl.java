 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.model.Category;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceCategorizationEngine categorizationEngine;

    // In-memory storage
    private final List<Invoice> invoiceStore = new ArrayList<>();

    public InvoiceServiceImpl(InvoiceCategorizationEngine categorizationEngine) {
        this.categorizationEngine = categorizationEngine;
    }

    @Override
    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        Category category = categorizationEngine.categorize(invoice, rules);
        invoice.setCategory(category);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceStore.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        invoice.setUserId(userId);
        invoice.setVendorId(vendorId);
        invoiceStore.add(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        List<Invoice> result = new ArrayList<>();
        for (Invoice i : invoiceStore) {
            if (i.getUserId().equals(userId)) {
                result.add(i);
            }
        }
        return result;
    }
}
