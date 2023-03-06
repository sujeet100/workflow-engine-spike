package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public interface ProcessEntity<T extends ProcessStep> {
    String getCity();

    @JsonIgnore
    T getPendingStep();

    int getRequestId();

    void setRequestId(int requestId);

    String getStatus();

    void setStatus(String status);

    AppUser getAppUserByRequestedByUserId();

    void setAppUserByRequestedByUserId(AppUser appUserByRequestedByUserId);

    Set<T> getSteps();

    void setSteps(Set<T> steps);
}
