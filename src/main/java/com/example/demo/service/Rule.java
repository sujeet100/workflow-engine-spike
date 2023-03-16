package com.example.demo.service;

import com.example.demo.entity.ProcessEntity;
import com.example.demo.entity.RuleData;

public interface Rule<T extends ProcessEntity> {
    boolean matches(RuleData ruleData, T entity);
}
