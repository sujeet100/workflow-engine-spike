package com.example.demo.repository;

import com.example.demo.entity.ProcessDef;
import org.springframework.data.repository.CrudRepository;

public interface ProcessDefRepository extends CrudRepository<ProcessDef, Integer> {
    ProcessDef findByName(String name);
}
