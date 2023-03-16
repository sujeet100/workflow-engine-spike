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
@Table(name = "step_transition", schema = "public", catalog = "workflow")
public class StepTransition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "process_id", nullable = true)
    private Integer processId;
    @Basic
    @Column(name = "current_step_id", nullable = true)
    private Integer currentStepId;
    @Basic
    @Column(name = "next_step_id", nullable = true)
    private Integer nextStepId;
    @Basic
    @Column(name = "action", nullable = true, length = 50)
    private String action;

    @Basic
    @Column(name = "condition", nullable = true, length = 50)
    private String condition;
    @ManyToOne
    @JoinColumn(name = "process_id", referencedColumnName = "process_id", insertable = false, updatable = false)
    private ProcessDef processDefByProcessId;
    @ManyToOne
    @JoinColumn(name = "current_step_id", referencedColumnName = "step_id", insertable = false, updatable = false)
    private StepDef stepDefByCurrentStepId;
    @ManyToOne
    @JoinColumn(name = "next_step_id", referencedColumnName = "step_id", insertable = false, updatable = false)
    private StepDef stepDefByNextStepId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getCurrentStepId() {
        return currentStepId;
    }

    public void setCurrentStepId(Integer currentStepId) {
        this.currentStepId = currentStepId;
    }

    public Integer getNextStepId() {
        return nextStepId;
    }

    public void setNextStepId(Integer nextStepId) {
        this.nextStepId = nextStepId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StepTransition that = (StepTransition) o;
        return id == that.id && Objects.equals(processId, that.processId) && Objects.equals(
                currentStepId,
                that.currentStepId) && Objects.equals(nextStepId, that.nextStepId) && Objects.equals(
                action,
                that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processId, currentStepId, nextStepId, action);
    }

    public ProcessDef getProcessDefByProcessId() {
        return processDefByProcessId;
    }

    public void setProcessDefByProcessId(ProcessDef processDefByProcessId) {
        this.processDefByProcessId = processDefByProcessId;
    }

    public StepDef getStepDefByCurrentStepId() {
        return stepDefByCurrentStepId;
    }

    public void setStepDefByCurrentStepId(StepDef stepDefByCurrentStepId) {
        this.stepDefByCurrentStepId = stepDefByCurrentStepId;
    }

    public StepDef getStepDefByNextStepId() {
        return stepDefByNextStepId;
    }

    public void setStepDefByNextStepId(StepDef stepDefByNextStepId) {
        this.stepDefByNextStepId = stepDefByNextStepId;
    }

    public RuleData getCondition() {
        return null;
    }

    public void setCondition(String rule) {
        this.condition = rule;
    }
}
