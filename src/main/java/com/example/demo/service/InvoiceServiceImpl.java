package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.model.Category;
import com.example.demo.util.InvoiceCategorizationEngine;

import java.util.List;

 

public class InvoiceServiceImpl {

    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        Category category = InvoiceCategorizationEngine.categorizeInvoice(invoice, rules);
        invoice.setCategory(category);
    }
}
