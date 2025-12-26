 package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "invoices",
    uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id","invoiceNumber"})
)
public class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vendor_id", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Vendor vendor;

    @NotBlank
    private String invoiceNumber;

    @NotNull @Positive
    private Double amount;

    @NotNull
    private LocalDate invoiceDate;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uploaded_by_id", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private User uploadedBy;

    private LocalDateTime uploadedAt;

    public Invoice() {}

    public Invoice(Vendor vendor, String invoiceNumber, Double amount,
                   LocalDate invoiceDate, String description, User uploadedBy) {
        this.vendor = vendor;
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.description = description;
        this.uploadedBy = uploadedBy;
    }

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public User getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(User uploadedBy) { this.uploadedBy = uploadedBy; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
