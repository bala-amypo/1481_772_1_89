 package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CategorizationRuleService ruleService;

    public InvoiceService(InvoiceRepository invoiceRepository, CategorizationRuleService ruleService){
        this.invoiceRepository = invoiceRepository;
        this.ruleService = ruleService;
    }

    public Invoice createInvoice(Invoice invoice){
        // Validate unique invoice number per vendor
        invoiceRepository.findByInvoiceNumberAndVendorId(invoice.getInvoiceNumber(), invoice.getVendor().getId())
                .ifPresent(i -> {throw new RuntimeException("Invoice number already exists for this vendor");});

        // Auto-categorization
        invoice.setCategory(autoCategorize(invoice));
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice){
        Invoice invoice = getInvoiceById(id);
        invoice.setAmount(updatedInvoice.getAmount());
        invoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
        invoice.setVendor(updatedInvoice.getVendor());
        invoice.setCategory(autoCategorize(invoice)); // recalc category
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id){
        invoiceRepository.deleteById(id);
    }

    private Category autoCategorize(Invoice invoice){
        List<CategorizationRule> rules = ruleService.getAllRules();
        String description = invoice.getDescription() != null ? invoice.getDescription().toLowerCase() : "";

        for(CategorizationRule rule : rules){
            String keyword = rule.getKeyword().toLowerCase();
            switch(rule.getMatchType()){
                case EXACT:
                    if(description.equals(keyword)) return rule.getCategory();
                    break;
                case CONTAINS:
                    if(description.contains(keyword)) return rule.getCategory();
                    break;
                case REGEX:
                    if(description.matches(keyword)) return rule.getCategory();
                    break;
            }
        }
        return null; // uncategorized
    }
}
