package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "leave_step", schema = "public", catalog = "workflow")
public class LeaveStep implements ProcessStep<Leave> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "step_id", nullable = false)
    private int stepId;

    @Basic
    @Column(name = "step_def_id", nullable = false)
    private int stepDefId;
    @Basic
    @Column(name = "leave_request_id", nullable = true)
    private Integer leaveRequestId;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true, length = 50)
    private StepStatus status;
    @Basic
    @Column(name = "remark", nullable = true, length = 50)
    private String remark;
    @Basic
    @Column(name = "approver_id", nullable = true)
    private Integer approverId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_request_id", referencedColumnName = "request_id", insertable = false, updatable = false)
    @JsonIgnore
    private Leave leaveByLeaveRequestId;
    @ManyToOne
    @JoinColumn(name = "approver_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private AppUser appUserByApproverId;


    public LeaveStep(Integer leaveRequestId, Integer stepDefId, StepStatus status, String remark, Integer approverId) {
        this.leaveRequestId = leaveRequestId;
        this.stepDefId = stepDefId;
        this.status = status;
        this.remark = remark;
        this.approverId = approverId;
    }

    public LeaveStep() {

    }

    @Override
    public int getStepId() {
        return stepId;
    }

    @Override
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    @Override
    public Integer getRequestId() {
        return leaveRequestId;
    }

    @Override
    public void setRequestId(Integer leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    @Override
    public StepStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(StepStatus status) {
        this.status = status;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Integer getApproverId() {
        return approverId;
    }

    @Override
    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeaveStep leaveStep = (LeaveStep) o;
        return stepId == leaveStep.stepId && Objects.equals(leaveRequestId,
                                                            leaveStep.leaveRequestId) && Objects.equals(
                status,
                leaveStep.status) && Objects.equals(remark, leaveStep.remark) && Objects.equals(
                approverId,
                leaveStep.approverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepId, leaveRequestId, status, remark, approverId);
    }

    public void setLeaveByLeaveRequestId(Leave leaveByLeaveRequestId) {
        this.leaveByLeaveRequestId = leaveByLeaveRequestId;
    }

    @JsonIgnore
    public AppUser getAppUserByApproverId() {
        return appUserByApproverId;
    }

    public void setAppUserByApproverId(AppUser appUserByApproverId) {
        this.appUserByApproverId = appUserByApproverId;
    }

    @Override
    public int getStepDefId() {
        return stepDefId;
    }

    @Override
    public void setStepDefId(int stepDefId) {
        this.stepDefId = stepDefId;
    }

    @Override
    @JsonIgnore
    public Leave getProcessInstanceByProcessInstanceId() {
        return leaveByLeaveRequestId;
    }
}
