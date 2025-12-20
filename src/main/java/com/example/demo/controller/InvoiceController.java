 package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Invoices Endpoints")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // UPLOAD
    @PostMapping("/upload/{userId}/{vendorId}")
    public Invoice uploadInvoice(
            @PathVariable Long userId,
            @PathVariable Long vendorId,
            @Valid @RequestBody Invoice invoice) {

        return invoiceService.uploadInvoice(userId, vendorId, invoice);
    }

    // CATEGORIZE
    @PostMapping("/categorize/{invoiceId}")
    public Invoice categorize(@PathVariable Long invoiceId) {
        return invoiceService.categorizeInvoice(invoiceId);
    }

    // USER INVOICES
    @GetMapping("/user/{userId}")
    public List<Invoice> userInvoices(@PathVariable Long userId) {
        return invoiceService.getInvoicesByUser(userId);
    }

    // SINGLE INVOICE
    @GetMapping("/{invoiceId}")
    public Invoice getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }
}
