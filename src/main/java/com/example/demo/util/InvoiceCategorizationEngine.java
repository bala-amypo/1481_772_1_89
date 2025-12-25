 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final InvoiceCategorizationEngine engine = new InvoiceCategorizationEngine();

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CategorizationRuleRepository ruleRepository) {
        this.invoiceRepository = invoiceRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        List<CategorizationRule> rules = ruleRepository.findAllByOrderByPriorityAsc();
        Category category = engine.categorize(invoice, rules);
        invoice.setCategory(category);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Category categorizeInvoice(Invoice invoice, List<CategorizationRule> rules) {
        return engine.categorize(invoice, rules);
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        return invoiceRepository.findByUserId(userId);
    }
}
