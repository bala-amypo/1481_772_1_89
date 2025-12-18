package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CategorizationRule;

@Repository
public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {

}
