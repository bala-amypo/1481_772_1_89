 package com.example.demo.service;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long id){
        return vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    public Vendor updateVendor(Long id, Vendor updatedVendor){
        Vendor vendor = getVendorById(id);
        vendor.setName(updatedVendor.getName());
        vendor.setEmail(updatedVendor.getEmail());
        return vendorRepository.save(vendor);
    }

    public void deleteVendor(Long id){
        vendorRepository.deleteById(id);
    }
}
