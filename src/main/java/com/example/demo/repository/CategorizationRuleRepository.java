 package com.example.demo.repository;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {
    List<CategorizationRule> findByCategoryOrderByPriorityAsc(Category category);
}
