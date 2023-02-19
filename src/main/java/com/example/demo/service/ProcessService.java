package com.example.demo.service;

import com.example.demo.entity.ProcessDef;
import com.example.demo.entity.StepDef;
import com.example.demo.repository.ProcessDefRepository;
import com.example.demo.repository.StepTransitionRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    private final StepTransitionRepository stepTransitionRepository;
    private final ProcessDefRepository processDefRepository;

    public ProcessService(StepTransitionRepository stepTransitionRepository,
                          ProcessDefRepository processDefRepository) {
        this.stepTransitionRepository = stepTransitionRepository;
        this.processDefRepository = processDefRepository;
    }

    ProcessDef getProcess(String name) {
        return processDefRepository.findByName(name);
    }

    StepDef getNextStep(Integer processId, Integer currentStepDefId, String action) {
        return stepTransitionRepository.findByProcessIdAndCurrentStepIdAndAction(processId,
                                                                                 currentStepDefId,
                                                                                 action)
                                       .getStepDefByNextStepId();
    }
}
