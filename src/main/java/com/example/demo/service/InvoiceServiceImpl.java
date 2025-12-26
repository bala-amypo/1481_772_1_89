 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.util.InvoiceCategorizationEngine;

public class InvoiceServiceImpl {
    private final InvoiceRepository invoiceRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final InvoiceCategorizationEngine engine;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              CategorizationRuleRepository ruleRepository,
                              InvoiceCategorizationEngine engine) {
        this.invoiceRepository = invoiceRepository;
        this.ruleRepository = ruleRepository;
        this.engine = engine;
    }

    // Dummy service methods for compilation
}
