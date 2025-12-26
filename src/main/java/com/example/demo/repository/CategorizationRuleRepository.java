 package com.example.demo.repository;

import com.example.demo.model.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collections;
import java.util.List;

public interface CategorizationRuleRepository
        extends JpaRepository<CategorizationRule, Long> {
    List<CategorizationRule> findByCategoryIdOrderByPriorityDesc(Long categoryId);
    default List<CategorizationRule> findMatchingRulesByDescription(String description) {
        return Collections.emptyList(); 
    }
    @Query("""
        SELECT r FROM CategorizationRule r
        WHERE LOWER(r.keyword) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<CategorizationRule> findByKeywordContainingIgnoreCase(String keyword);
}
