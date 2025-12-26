 package com.example.demo.service.impl;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.entity.Invoice;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CategorizationRuleRepository ruleRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CategorizationRuleRepository ruleRepository){
        this.invoiceRepository = invoiceRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Invoice createInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public Invoice updateInvoice(Long id, Invoice invoice){
        Invoice existing = getInvoiceById(id);
        existing.setInvoiceNumber(invoice.getInvoiceNumber());
        existing.setAmount(invoice.getAmount());
        existing.setVendor(invoice.getVendor());
        existing.setDate(invoice.getDate());
        return invoiceRepository.save(existing);
    }

    @Override
    public void deleteInvoice(Long id){
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice categorizeInvoice(Invoice invoice){
        List<CategorizationRule> rules = ruleRepository.findAllByOrderByPriorityAsc();
        for(CategorizationRule rule : rules){
            if(rule.getMatchType().equalsIgnoreCase("EXACT") && invoice.getInvoiceNumber().equals(rule.getPattern())){
                invoice.setCategory(rule.getCategory());
                break;
            } else if(rule.getMatchType().equalsIgnoreCase("CONTAINS") && invoice.getInvoiceNumber().contains(rule.getPattern())){
                invoice.setCategory(rule.getCategory());
                break;
            } else if(rule.getMatchType().equalsIgnoreCase("REGEX") && invoice.getInvoiceNumber().matches(rule.getPattern())){
                invoice.setCategory(rule.getCategory());
                break;
            }
        }
        return invoiceRepository.save(invoice);
    }
}
