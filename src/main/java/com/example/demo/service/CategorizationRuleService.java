package com.example.demo.service;

import java.util.List;
import com.example.demo.model.CategorizationRule;

public interface CategorizationRuleService {
    CategorizationRule saveRule(CategorizationRule rule);
    List<CategorizationRule> getAllRules();
    CategorizationRule getRuleById(Long id);
    CategorizationRule updateRule(Long id, CategorizationRule rule);
    void deleteRule(Long id);
}
