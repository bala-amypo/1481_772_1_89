 package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public Invoice createInvoice(
            @Valid @RequestBody Invoice invoice,
            @RequestParam Long vendorId,
            @RequestParam Long userId
    ) {
        return invoiceService.createInvoice(invoice, vendorId, userId);
    }
}
