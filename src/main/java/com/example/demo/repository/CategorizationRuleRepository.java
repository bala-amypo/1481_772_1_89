 package com.example.demo.repository;

import com.example.demo.model.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {

    // 🔹 Now this will work because description exists in entity
    List<CategorizationRule> findMatchingRulesByDescription(String description);
}
