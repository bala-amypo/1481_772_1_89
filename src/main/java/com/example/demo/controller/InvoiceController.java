 package com.example.demo.controller;

import com.example.demo.dto.InvoiceUploadRequest;
import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Invoices")
@SecurityRequirement(name = "bearerAuth")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) { this.invoiceService = invoiceService; }

    @PostMapping("/upload/{userId}/{vendorId}")
    public Invoice uploadInvoice(@PathVariable Long userId,
                                 @PathVariable Long vendorId,
                                 @RequestBody InvoiceUploadRequest dto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setAmount(dto.getAmount());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDescription(dto.getDescription());
        return invoiceService.uploadInvoice(userId, vendorId, invoice);
    }

    @PostMapping("/categorize/{invoiceId}")
    public Invoice categorizeInvoice(@PathVariable Long invoiceId) {
        return invoiceService.categorizeInvoice(invoiceId);
    }

    @GetMapping("/user/{userId}")
    public List<Invoice> getInvoicesByUser(@PathVariable Long userId) {
        return invoiceService.getInvoicesByUser(userId);
    }

    @GetMapping("/{invoiceId}")
    public Invoice getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }
}
