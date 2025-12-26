 package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    private final CategorizationRuleRepository ruleRepository;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        if (rule.getKeyword() == null || rule.getKeyword().isEmpty()) {
            throw new IllegalArgumentException("Rule keyword cannot be empty");
        }
        return ruleRepository.save(rule);
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public CategorizationRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public CategorizationRule updateRule(Long id, CategorizationRule rule) {
        CategorizationRule existing = getRuleById(id);

        existing.setKeyword(rule.getKeyword());
        existing.setMatchType(rule.getMatchType());
        existing.setPriority(rule.getPriority());
        existing.setCategory(rule.getCategory());

        return ruleRepository.save(existing);
    }

    @Override
    public void deleteRule(Long id) {
        CategorizationRule rule = getRuleById(id);
        ruleRepository.delete(rule);
    }
}
