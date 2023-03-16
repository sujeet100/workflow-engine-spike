package com.example.demo.repository;

import com.example.demo.entity.StepDef;
import com.example.demo.entity.StepTransition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StepTransitionRepository extends CrudRepository<StepTransition, Integer> {

    List<StepTransition> findByProcessIdAndCurrentStepIdAndAction(Integer processId,
                                                                  Integer currentStepId,
                                                                  String action);
}
