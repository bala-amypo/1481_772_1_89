 package com.example.demo.service;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationRuleService {

    private final CategorizationRuleRepository ruleRepository;

    public CategorizationRuleService(CategorizationRuleRepository ruleRepository){
        this.ruleRepository = ruleRepository;
    }

    public CategorizationRule createRule(CategorizationRule rule){
        return ruleRepository.save(rule);
    }

    public List<CategorizationRule> getAllRules(){
        return ruleRepository.findAllByOrderByPriorityAsc();
    }

    public CategorizationRule getRuleById(Long id){
        return ruleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    public CategorizationRule updateRule(Long id, CategorizationRule updatedRule){
        CategorizationRule rule = getRuleById(id);
        rule.setKeyword(updatedRule.getKeyword());
        rule.setMatchType(updatedRule.getMatchType());
        rule.setPriority(updatedRule.getPriority());
        rule.setCategory(updatedRule.getCategory());
        return ruleRepository.save(rule);
    }

    public void deleteRule(Long id){
        ruleRepository.deleteById(id);
    }
}
