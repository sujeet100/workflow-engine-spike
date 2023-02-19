package com.example.demo.service;

import com.example.demo.LeaveController;
import com.example.demo.entity.Approver;
import com.example.demo.entity.Leave;
import com.example.demo.entity.LeaveStatus;
import com.example.demo.entity.LeaveStep;
import com.example.demo.entity.ProcessDef;
import com.example.demo.entity.StepDef;
import com.example.demo.entity.StepStatus;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.LeaveStepRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.entity.StepAction.APPROVE;
import static com.example.demo.entity.StepStatus.COMPLETED;
import static com.example.demo.entity.StepStatus.PENDING;

@Service
public class LeaveService {
    public static final String LEAVE_APPLICATION_PROCESS_NAME = "LEAVE_APPLICATION";
    private final LeaveRepository leaveRepository;
    private final LeaveStepRepository leaveStepRepository;
    private final ApproverService approverService;

    private final ProcessService processService;


    public LeaveService(LeaveRepository leaveRepository,
                        LeaveStepRepository leaveStepRepository,
                        ApproverService approverService, ProcessService processService) {

        this.leaveRepository = leaveRepository;
        this.leaveStepRepository = leaveStepRepository;
        this.approverService = approverService;
        this.processService = processService;
    }

    public Leave initLeave(Leave leaveRequest) {
        Leave leave = leaveRepository.save(leaveRequest);
        StepDef firstStepDef = processService.getInitialStep();

        Approver approver = approverService.getApprover(leave.getCity(), firstStepDef.getApproverRole());
        createPendingStep(leave.getRequestId(), firstStepDef.getStepId(), approver.getId());
        return leave;
    }

    public Leave approveCurrentLeaveStep(Integer userId, Integer leaveId, String remark) {
        Leave leave = findLeaveById(leaveId);
        LeaveStep currentStep = leave.getPendingStep();
        if (!currentStep.getApproverId().equals(userId)) {
            throw new RuntimeException("The request %s is not pending for approval with you. You cannot approve it."
                                               .formatted(leaveId));
        }
        completeStep(currentStep, remark);

        nextStep(leaveId, leave.getCity(), currentStep.getStepDefId());
        return findLeaveById(leaveId);
    }

    public Leave findLeaveById(Integer leaveId) {
        return leaveRepository.findById(leaveId)
                              .orElseThrow(() -> new RuntimeException("Leave with leaveRequestId: %s not found".formatted(
                                      leaveId)));
    }

    public void nextStep(Integer leaveId, String city, Integer pendingStepDefId) {
        ProcessDef leaveProcessDef = processService.getProcess(LEAVE_APPLICATION_PROCESS_NAME);
        StepDef nextStep = processService.getNextStep(leaveProcessDef.getProcessId(), pendingStepDefId, APPROVE);
        if (nextStep.isTerminalStep()) {
            createCompletedStep(leaveId, nextStep.getStepId(), null);
            completeLeaveRequest(leaveId, LeaveStatus.APPROVED);
        } else {
            Integer approverId = approverService.getApprover(city, nextStep.getApproverRole()).getUserId();
            createPendingStep(leaveId, nextStep.getStepId(), approverId);
        }

    }

    private void completeLeaveRequest(Integer leaveId, LeaveStatus leaveStatus) {
        Leave leave = findLeaveById(leaveId);
        leave.setStatus(leaveStatus);
        leaveRepository.save(leave);
    }

    public void completeStep(LeaveStep pendingStep, String remark) {
        pendingStep.setRemark(remark);
        pendingStep.setStatus(StepStatus.COMPLETED);
        leaveStepRepository.save(pendingStep);
    }

    public LeaveStep createPendingStep(Integer leaveRequestId, Integer stepDefId, Integer approverId) {
        return createStep(leaveRequestId, stepDefId, PENDING, approverId);
    }

    public LeaveStep createCompletedStep(Integer leaveRequestId, Integer stepDefId, Integer approverId) {
        return createStep(leaveRequestId, stepDefId, COMPLETED, approverId);
    }

    public LeaveStep createStep(Integer leaveRequestId, Integer stepDefId, StepStatus stepStatus, Integer approverId) {
        return leaveStepRepository.save(new LeaveStep(leaveRequestId,
                                                      stepDefId,
                                                      stepStatus,
                                                      null,
                                                      approverId));
    }

    public List<Leave> getLeavesPendingApproval(Integer userId, LeaveController leaveController) {
        return leaveStepRepository.findByApproverIdAndStatus(userId, PENDING)
                                  .stream()
                                  .map(LeaveStep::getLeaveByLeaveRequestId).toList();
    }
}
