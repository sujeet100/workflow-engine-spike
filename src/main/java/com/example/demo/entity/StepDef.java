package com.example.demo.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "step_def", schema = "public", catalog = "workflow")
public class StepDef {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "step_id", nullable = false)
    private int stepId;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "process_status", nullable = false, length = 50)
    private String processStatus;
    @Basic
    @Column(name = "process_id", nullable = true)
    private Integer processId;
    @Basic
    @Column(name = "approver_role", nullable = true, length = 50)
    private String approverRole;
    @Basic
    @Column(name = "is_initial_step", nullable = true)
    private Boolean isInitialStep;

    @Basic
    @Column(name = "is_terminal_step", nullable = true)
    private Boolean isTerminalStep;
    @ManyToOne
    @JoinColumn(name = "process_id", referencedColumnName = "process_id", insertable = false, updatable = false)
    private ProcessDef processDefByProcessId;

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getApproverRole() {
        return approverRole;
    }

    public void setApproverRole(String approverRole) {
        this.approverRole = approverRole;
    }

    public Boolean getInitialStep() {
        return isInitialStep;
    }

    public void setInitialStep(Boolean initialStep) {
        isInitialStep = initialStep;
    }

    public Boolean isTerminalStep() {
        return isTerminalStep;
    }

    public void setTerminalStep(Boolean terminalStep) {
        isTerminalStep = terminalStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StepDef stepDef = (StepDef) o;
        return stepId == stepDef.stepId && Objects.equals(name, stepDef.name) && Objects.equals(
                processId,
                stepDef.processId) &&
                Objects.equals(approverRole, stepDef.approverRole) &&
                Objects.equals(isInitialStep, stepDef.isInitialStep) &&
                Objects.equals(isTerminalStep, stepDef.isTerminalStep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepId, name, processId, approverRole, isInitialStep, isTerminalStep);
    }

    public ProcessDef getProcessDefByProcessId() {
        return processDefByProcessId;
    }

    public void setProcessDefByProcessId(ProcessDef processDefByProcessId) {
        this.processDefByProcessId = processDefByProcessId;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public Boolean getTerminalStep() {
        return isTerminalStep;
    }
}
