 package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "vendors")
public class Vendor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true)
    private String vendorName;

    @Email @NotBlank
    private String contactEmail;

    private String address;

    private LocalDateTime createdAt;
    // @JsonIgnoreProperties({"favoriteVendors","users"})
    @ManyToMany(mappedBy = "favoriteVendors")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

  
    @OneToMany(mappedBy = "vendor")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<Invoice> invoices;

    public Vendor() {}

    public Vendor(String vendorName, String contactEmail, String address,Set<User> users) {
        this.vendorName = vendorName;
        this.contactEmail = contactEmail;
        this.address = address;
        this.users=users;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Set<User> getUsers() {
        return users;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // public List<Invoice> getInvoices() { return invoices; }
    // public void setInvoices(List<Invoice> invoices) { this.invoices = invoices; }
}
