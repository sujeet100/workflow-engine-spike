package com.example.demo.service;

import com.example.demo.entity.Leave;
import com.example.demo.entity.RuleData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LeavesTransitionRulesService implements TransitionRulesService<Leave> {
    Map<String, Rule<Leave>> transitionRules = new HashMap<>();

    public LeavesTransitionRulesService(NoOfDaysRule noOfDaysRule, AgingRule agingRule) {
        transitionRules.put("NO_OF_DAYS_MORE_THAN", noOfDaysRule);
        transitionRules.put("NO_OF_DAYS_MORE_THAN_20", agingRule);
    }

    public boolean matchesRule(RuleData ruleData, Leave leave) {
        return transitionRules.get(ruleData.getName()).matches(ruleData, leave);
    }
}

