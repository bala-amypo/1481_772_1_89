 package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public MatchType getMatchType() { return matchType; }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    // ✅ REQUIRED BY TESTS
    public void setMatchType(String matchType) {
        this.matchType = MatchType.valueOf(matchType);
    }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
