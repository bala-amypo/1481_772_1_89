 package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vendors", uniqueConstraints = {
        @UniqueConstraint(columnNames = "vendorName")
})
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String vendorName;

    @Email
    @NotBlank
    private String contactEmail;

    private String address;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "favoriteVendors")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "vendor")
    private Set<Invoice> invoices = new HashSet<>();

    public Vendor() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
