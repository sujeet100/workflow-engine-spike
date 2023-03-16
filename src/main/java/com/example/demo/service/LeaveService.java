package com.example.demo.service;

import com.example.demo.entity.Leave;
import com.example.demo.entity.LeaveStep;
import com.example.demo.entity.StepStatus;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.LeaveStepRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeaveService extends WorkflowServiceImpl<Leave, LeaveStep> {
    public static final String LEAVE_APPLICATION_PROCESS_NAME = "LEAVE_APPLICATION";

    public LeaveService(LeaveStepRepository processStepRepository,
                        LeaveRepository processEntityRepository,
                        ApproverService approverService,
                        ProcessService processService) {
        super(LEAVE_APPLICATION_PROCESS_NAME,
              processStepRepository, processEntityRepository, approverService, transitionRulesService, processService);
    }


    @Override
    public LeaveStep newStep(Integer leaveRequestId,
                             Integer stepDefId,
                             StepStatus stepStatus,
                             String remark,
                             Integer approverId) {
        return new LeaveStep(leaveRequestId, stepDefId, stepStatus, remark, approverId);
    }

    @Override
    public Optional<String> getCondition(Leave leave) {
        return leave.getNumberOfDays() > 10 ? Optional.of("LONG_LEAVE") : Optional.empty();
    }
}
