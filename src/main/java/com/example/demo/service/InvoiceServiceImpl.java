 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceCategorizationEngine categorizationEngine;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              InvoiceCategorizationEngine categorizationEngine) {
        this.invoiceRepository = invoiceRepository;
        this.categorizationEngine = categorizationEngine;
    }

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        invoice.setUserId(userId);
        invoice.setVendorId(vendorId);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        categorizationEngine.categorize(invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        return invoiceRepository.findByUserId(userId);
    }
}
