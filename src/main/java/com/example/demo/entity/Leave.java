package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.persistence.OneToMany;

import java.util.Objects;
import java.util.Set;

import static com.example.demo.entity.StepStatus.PENDING;

@Entity
public class Leave {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "request_id", nullable = false)
    private int requestId;
    @Basic
    @Column(name = "number_of_days", nullable = true)
    private Integer numberOfDays;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true, length = 50)
    private LeaveStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requested_by_user_id")
    private AppUser appUserByRequestedByUserId;

    @OneToMany(mappedBy = "leaveRequestId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<LeaveStep> steps;

    public String getCity() {
        return getAppUserByRequestedByUserId().getCity();
    }

    @JsonIgnore
    public LeaveStep getPendingStep() {
        return getSteps().stream()
                         .filter(x -> x.getStatus().equals(PENDING))
                         .findFirst()
                         .orElseThrow(() -> new RuntimeException("No pending step found"));
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }


    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Leave leave = (Leave) o;
        return requestId == leave.requestId && Objects.equals(numberOfDays,
                                                              leave.numberOfDays) && Objects.equals(status,
                                                                                                    leave.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, numberOfDays, status);
    }

    public AppUser getAppUserByRequestedByUserId() {
        return appUserByRequestedByUserId;
    }

    public void setAppUserByRequestedByUserId(AppUser appUserByRequestedByUserId) {
        this.appUserByRequestedByUserId = appUserByRequestedByUserId;
    }

    public Set<LeaveStep> getSteps() {
        return steps;
    }

    public void setSteps(Set<LeaveStep> steps) {
        this.steps = steps;
    }
}
