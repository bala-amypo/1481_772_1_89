 package com.example.demo.service.impl;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.User;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;

    public InvoiceServiceImpl(
            InvoiceRepository invoiceRepository,
            VendorRepository vendorRepository,
            UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Invoice createInvoice(Invoice invoice, Long vendorId, Long userId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        invoiceRepository.findByVendorIdAndInvoiceNumber(
                vendorId, invoice.getInvoiceNumber()
        ).ifPresent(i -> {
            throw new RuntimeException("Invoice number already exists for this vendor");
        });

        invoice.setVendor(vendor);
        invoice.setUploadedBy(user);

        return invoiceRepository.save(invoice);
    }
}
