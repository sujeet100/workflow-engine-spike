package com.example.demo.service;

import com.example.demo.entity.Approver;
import com.example.demo.entity.ProcessDef;
import com.example.demo.entity.ProcessEntity;
import com.example.demo.entity.ProcessStep;
import com.example.demo.entity.StepDef;
import com.example.demo.entity.StepStatus;
import com.example.demo.repository.ProcessEntityRepository;
import com.example.demo.repository.ProcessStepRepository;

import java.util.List;

import static com.example.demo.entity.StepAction.APPROVE;
import static com.example.demo.entity.StepStatus.COMPLETED;
import static com.example.demo.entity.StepStatus.PENDING;

public abstract class WorkflowServiceImpl<T extends ProcessEntity<? extends ProcessStep>, R extends ProcessStep> implements WorkflowService<T, R> {
    public final String processName;
    private final ProcessEntityRepository<T> processEntityRepository;
    private final ApproverService approverService;
    private final ProcessStepRepository<R> processStepRepository;

    private final ProcessService processService;


    public WorkflowServiceImpl(String processName, ProcessStepRepository<R> processStepRepository,
                               ProcessEntityRepository<T> processEntityRepository,
                               ApproverService approverService, ProcessService processService) {
        this.processName = processName;
        this.processStepRepository = processStepRepository;
        this.processEntityRepository = processEntityRepository;
        this.approverService = approverService;
        this.processService = processService;
    }

    @Override
    public T initProcessInstance(T processRequest) {
        T processInstance = processEntityRepository.save(processRequest);
        StepDef firstStepDef = processService.getInitialStep(processName);

        Approver approver = approverService.getApprover(processInstance.getCity(), firstStepDef.getApproverRole());
        createPendingStep(processInstance.getRequestId(), firstStepDef.getStepId(), approver.getId());
        return updateProcessInstanceStatus(processInstance, firstStepDef.getProcessStatus());
    }

    @Override
    public T updateProcessInstanceStatus(T processInstance, String processStatus) {
        processInstance.setStatus(processStatus);
        return processEntityRepository.save(processInstance);
    }

    @Override
    public T approveCurrentProcessStep(Integer userId, Integer processInstanceId, String remark) {
        T processInstance = findProcessInstanceById(processInstanceId);
        R currentStep = (R) processInstance.getPendingStep();
        if (!currentStep.getApproverId().equals(userId)) {
            throw new RuntimeException("The request %s is not pending for approval with you. You cannot approve it."
                                               .formatted(processInstanceId));
        }
        completeStep(currentStep, remark);

        nextStep(processInstanceId, processInstance.getCity(), currentStep.getStepDefId());
        return findProcessInstanceById(processInstanceId);
    }

    @Override
    public T findProcessInstanceById(Integer processInstanceId) {
        return processEntityRepository.findById(processInstanceId)
                                      .orElseThrow(() -> new RuntimeException(
                                              "Process Instance with requestId: %s not found".formatted(
                                                      processInstanceId)));
    }

    @Override
    public void nextStep(Integer processInstanceId, String city, Integer pendingStepDefId) {
        ProcessDef processDef = processService.getProcess(processName);
        StepDef nextStep = processService.getNextStep(processDef.getProcessId(), pendingStepDefId, APPROVE);
        Integer approverId = nextStep.isTerminalStep() ? null : approverService.getApprover(city,
                                                                                            nextStep.getApproverRole())
                                                                               .getUserId();

        createPendingStep(processInstanceId, nextStep.getStepId(), approverId);
        updateProcessInstanceStatus(findProcessInstanceById(processInstanceId), nextStep.getProcessStatus());

    }

    @Override
    public void completeStep(R pendingStep, String remark) {
        pendingStep.setRemark(remark);
        pendingStep.setStatus(StepStatus.COMPLETED);
        processStepRepository.save(pendingStep);
    }

    @Override
    public void completeProcessInstance(Integer processInstanceId, String processInstanceStatus) {
        T processInstance = findProcessInstanceById(processInstanceId);
        processInstance.setStatus(processInstanceStatus);
        processEntityRepository.save(processInstance);
    }

    @Override
    public R createPendingStep(Integer processInstanceId, Integer stepDefId, Integer approverId) {
        return createStep(processInstanceId, stepDefId, PENDING, approverId);
    }

    @Override
    public R createCompletedStep(Integer processInstanceId, Integer stepDefId, Integer approverId) {
        return createStep(processInstanceId, stepDefId, COMPLETED, approverId);
    }

    @Override
    public R createStep(Integer processInstanceId, Integer stepDefId, StepStatus stepStatus, Integer approverId) {
        return processStepRepository.save(newStep(processInstanceId,
                                                  stepDefId,
                                                  stepStatus,
                                                  null,
                                                  approverId));
    }

    @Override
    public List<T> getProcessInstancesPendingApproval(Integer userId) {
        return processStepRepository.findByApproverIdAndStatus(userId, PENDING)
                                    .stream()
                                    .map(x -> (T) x.getProcessInstanceByProcessInstanceId()).toList();
    }

    public abstract R newStep(Integer processInstanceId,
                              Integer stepDefId,
                              StepStatus stepStatus,
                              String remark,
                              Integer approverId);
}
