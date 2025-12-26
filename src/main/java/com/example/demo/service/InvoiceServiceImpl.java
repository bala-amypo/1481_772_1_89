 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import com.example.demo.model.Vendor;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserRepository userRepository, VendorRepository vendorRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
    }

    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        User user = userRepository.findById(userId).orElseThrow();
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();

        invoice.setUploadedBy(user);
        invoice.setVendor(vendor);

        return invoiceRepository.save(invoice);
    }
}
