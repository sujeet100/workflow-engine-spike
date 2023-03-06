package com.example.demo.repository;

import com.example.demo.entity.ProcessEntity;
import com.example.demo.entity.ProcessStep;

import java.util.Optional;

public interface ProcessEntityRepository<T extends ProcessEntity<? extends ProcessStep>> {
    T save(T processEntity);

    Optional<T> findById(Integer processInstanceId);
}
