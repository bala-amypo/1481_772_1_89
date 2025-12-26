 package com.example.demo.service;

import com.example.demo.model.CategorizationRule;

import java.util.List;

public interface CategorizationRuleService {
    CategorizationRule createRule(CategorizationRule rule);
    List<CategorizationRule> getAllRules();
    CategorizationRule getRuleById(Long id);
    CategorizationRule updateRule(Long id, CategorizationRule rule);
    void deleteRule(Long id);
}
