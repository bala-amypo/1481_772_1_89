 package com.example.demo.service;

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
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public CategorizationRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Rule not found with id " + id
                ));
    }

    @Override
    public CategorizationRule saveRule(CategorizationRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public CategorizationRule updateRule(Long id, CategorizationRule rule) {
        CategorizationRule existing = getRuleById(id);

        existing.setRuleName(rule.getRuleName());
        existing.setPattern(rule.getPattern());
        existing.setPriority(rule.getPriority());

        return ruleRepository.save(existing);
    }

    @Override
    public void deleteRule(Long id) {
        CategorizationRule existing = getRuleById(id);
        ruleRepository.delete(existing);
    }
}
