package com.example.demo.repository;

import com.example.demo.entity.LeaveStep;
import com.example.demo.entity.StepStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveStepRepository extends CrudRepository<LeaveStep, Integer> {

    List<LeaveStep> findByApproverIdAndStatus(Integer approverId, StepStatus status);
}
