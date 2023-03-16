package com.example.demo.service;

import com.example.demo.entity.Leave;
import com.example.demo.entity.RuleData;
import org.springframework.stereotype.Service;

@Service
public class AgingRule implements Rule<Leave> {
    @Override
    public boolean matches(RuleData ruleData, Leave entity) {
        return entity.getNumberOfDays() > 20;
    }
}
