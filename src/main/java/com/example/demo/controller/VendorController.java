 package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 
 
  @RestController
@RequestMapping("/api/vendors")
@Tag(name = "Vendors Endpoints")
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor v) {
        return service.createVendor(v);
    }

    @GetMapping
    public List<Vendor> all() {
        return service.getAllVendors();
    }

    @GetMapping("/{id}")
    public Vendor get(@PathVariable Long id) {
        return service.getVendor(id);
    }
}
