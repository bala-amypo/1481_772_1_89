 package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 
 
 @RestController
@RequestMapping("/api/invoices")
@Tag(name = "Invoices Endpoints")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/upload/{userId}/{vendorId}")
    public Invoice upload(@PathVariable Long userId,
                          @PathVariable Long vendorId,
                          @RequestBody Invoice invoice) {
        return service.uploadInvoice(userId, vendorId, invoice);
    }

    @PostMapping("/categorize/{id}")
    public Invoice categorize(@PathVariable Long id) {
        return service.categorizeInvoice(id);
    }

    @GetMapping("/user/{id}")
    public List<Invoice> byUser(@PathVariable Long id) {
        return service.getInvoicesByUser(id);
    }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable Long id) {
        return service.getInvoice(id);
    }
}
