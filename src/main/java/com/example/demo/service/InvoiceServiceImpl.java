 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.RuleRepository;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final RuleRepository ruleRepository;
    private final InvoiceCategorizationEngine categorizationEngine;

    public InvoiceServiceImpl(
            InvoiceRepository invoiceRepository,
            RuleRepository ruleRepository,
            InvoiceCategorizationEngine categorizationEngine) {
        this.invoiceRepository = invoiceRepository;
        this.ruleRepository = ruleRepository;
        this.categorizationEngine = categorizationEngine;
    }

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        // user & vendor already mapped as entity in Invoice
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = getInvoice(invoiceId);

        List<Rule> rules = ruleRepository.findAllByOrderByPriorityAsc();

        categorizationEngine.applyRules(invoice, rules);

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }
}
