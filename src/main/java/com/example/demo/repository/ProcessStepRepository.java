package com.example.demo.repository;

import com.example.demo.entity.ProcessStep;
import com.example.demo.entity.StepStatus;

import java.util.List;

public interface ProcessStepRepository<T extends ProcessStep> {
    T save(T processEntity);

    List<T> findByApproverIdAndStatus(Integer approverId, StepStatus status);
}
