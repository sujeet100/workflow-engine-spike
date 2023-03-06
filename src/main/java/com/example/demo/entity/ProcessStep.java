package com.example.demo.entity;

public interface ProcessStep<T extends ProcessEntity> {
    int getStepId();

    void setStepId(int stepId);

    Integer getRequestId();

    void setRequestId(Integer processInstanceId);

    StepStatus getStatus();

    void setStatus(StepStatus status);

    String getRemark();

    void setRemark(String remark);

    Integer getApproverId();

    void setApproverId(Integer approverId);

    int getStepDefId();

    void setStepDefId(int stepDefId);

    T getProcessInstanceByProcessInstanceId();


}
