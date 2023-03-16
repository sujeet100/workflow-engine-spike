package com.example.demo.service;

import com.example.demo.entity.ProcessEntity;
import com.example.demo.entity.RuleData;

public interface TransitionRulesService<T extends ProcessEntity> {

    boolean matchesRule(RuleData ruleName, T leave);
}
