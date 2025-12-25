 package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/{vendorId}")
    public Invoice upload(@PathVariable Long userId,
                          @PathVariable Long vendorId,
                          @RequestBody Invoice invoice) {
        return service.uploadInvoice(userId, vendorId, invoice);
    }

    @PostMapping("/categorize/{id}")
    public Invoice categorize(@PathVariable Long id) {
        return service.categorizeInvoice(id);
    }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable Long id) {
        return service.getInvoice(id);
    }

    @GetMapping("/user/{userId}")
    public List<Invoice> byUser(@PathVariable Long userId) {
        return service.getInvoicesByUser(userId);
    }
}
