package com.example.demo.service;

import com.example.demo.entity.ProcessDef;
import com.example.demo.entity.StepDef;
import com.example.demo.entity.StepTransition;
import com.example.demo.repository.ProcessDefRepository;
import com.example.demo.repository.StepTransitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    private final StepTransitionRepository stepTransitionRepository;
    private final ProcessDefRepository processDefRepository;

    public ProcessService(StepTransitionRepository stepTransitionRepository,
                          ProcessDefRepository processDefRepository) {
        this.stepTransitionRepository = stepTransitionRepository;
        this.processDefRepository = processDefRepository;
    }

    public ProcessDef getProcess(String name) {
        return processDefRepository.findByName(name);
    }

    public StepDef getNextStep(Integer processId, Integer currentStepDefId, String action) {
        return stepTransitionRepository.findByProcessIdAndCurrentStepIdAndAction(processId,
                                                                                 currentStepDefId,
                                                                                 action)
                                       .get(0)
                                       .getStepDefByNextStepId();
    }


    public List<StepTransition> getNextSteps(Integer processId, Integer currentStepDefId, String action) {
        return stepTransitionRepository.findByProcessIdAndCurrentStepIdAndAction(processId,
                                                                                 currentStepDefId,
                                                                                 action);
    }

    public StepDef getInitialStep(String processName) {
        ProcessDef processDef = processDefRepository.findByName(processName);
        return processDef.getSteps()
                         .stream()
                         .filter(StepDef::getInitialStep)
                         .findFirst()
                         .orElseThrow(() -> new RuntimeException(
                                 "Incorrect process definition. No initial step found."));
    }
}
