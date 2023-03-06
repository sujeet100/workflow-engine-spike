package com.example.demo.service;

import com.example.demo.entity.ProcessEntity;
import com.example.demo.entity.ProcessStep;
import com.example.demo.entity.StepStatus;

import java.util.List;

public interface WorkflowService<T extends ProcessEntity<? extends ProcessStep>, R extends ProcessStep> {
    T initProcessInstance(T request);

    T updateProcessInstanceStatus(T processInstance, String processStatus);

    T approveCurrentProcessStep(Integer userId, Integer processInstanceId, String remark);

    T findProcessInstanceById(Integer processInstanceId);

    void nextStep(Integer processInstanceId, String city, Integer pendingStepDefId);

    void completeProcessInstance(Integer processInstanceId, String processInstanceStatus);

    void completeStep(R pendingStep, String remark);

    R createPendingStep(Integer processInstanceId, Integer stepDefId, Integer approverId);

    R createCompletedStep(Integer processInstanceId, Integer stepDefId, Integer approverId);

    R createStep(Integer processInstanceId, Integer stepDefId, StepStatus stepStatus, Integer approverId);

    List<T> getProcessInstancesPendingApproval(Integer userId);
}
