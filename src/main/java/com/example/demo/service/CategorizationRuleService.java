 package com.example.demo.service;

import com.example.demo.entity.CategorizationRule;
import java.util.List;

public interface CategorizationRuleService {
    CategorizationRule createRule(CategorizationRule rule);
    CategorizationRule getRuleById(Long id);
    List<CategorizationRule> getAllRules();
    CategorizationRule updateRule(Long id, CategorizationRule rule);
    void deleteRule(Long id);
}
