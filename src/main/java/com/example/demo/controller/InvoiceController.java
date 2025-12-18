 package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final UserService userService;

    public InvoiceController(InvoiceService invoiceService, UserService userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @PostMapping("/upload/{userId}")
    public Invoice uploadInvoice(@PathVariable Long userId, @RequestBody Invoice invoice) {
        User user = userService.getUserById(userId);
        invoice.setUploadedBy(user);
        return invoiceService.uploadInvoice(invoice);
    }

    @GetMapping("/user/{userId}")
    public List<Invoice> getInvoicesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return invoiceService.getInvoicesByUser(user);
    }

    @GetMapping("/{invoiceId}")
    public Invoice getInvoiceById(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }
}
