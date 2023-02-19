package com.example.demo.repository;

import com.example.demo.entity.Approver;
import com.example.demo.entity.StepDef;
import org.springframework.data.repository.CrudRepository;

public interface StepDefRepository extends CrudRepository<StepDef, Integer> {
}
