package com.atait.assignment.funds.repository;

import com.atait.assignment.funds.entity.ValidationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidationRuleRepository extends JpaRepository<ValidationRule, UUID> {
}
