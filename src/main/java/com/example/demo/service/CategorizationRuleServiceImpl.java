package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
//import com.example.demo.service.CategorizationRuleService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    @Autowired
    private CategorizationRuleRepository ruleRepository;

    @Override
    public CategorizationRule saveRule(CategorizationRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public CategorizationRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategorizationRule not found with id: " + id));
    }

    @Override
    public CategorizationRule updateRule(Long id, CategorizationRule rule) {
        CategorizationRule existingRule = getRuleById(id);
        existingRule.setRuleName(rule.getRuleName());
        existingRule.setPattern(rule.getPattern());
        existingRule.setCategory(rule.getCategory());
        return ruleRepository.save(existingRule);
    }

    @Override
    public void deleteRule(Long id) {
        CategorizationRule existingRule = getRuleById(id);
        ruleRepository.delete(existingRule);
    }
}
