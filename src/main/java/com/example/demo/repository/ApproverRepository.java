package com.example.demo.repository;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.Approver;
import org.springframework.data.repository.CrudRepository;

public interface ApproverRepository extends CrudRepository<Approver, Integer> {

    Approver findByApproverRoleAndCity(String approverRole, String city);
}
