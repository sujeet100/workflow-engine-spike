package com.example.demo.service;

import com.example.demo.entity.Approver;
import com.example.demo.repository.ApproverRepository;
import org.springframework.stereotype.Service;

@Service
public class ApproverService {
    private final ApproverRepository approverRepository;

    public ApproverService(ApproverRepository approverRepository) {
        this.approverRepository = approverRepository;
    }

    public Approver getApprover(String city, String approverRole) {
        return approverRepository.findByApproverRoleAndCity(approverRole, city);
    }
}
