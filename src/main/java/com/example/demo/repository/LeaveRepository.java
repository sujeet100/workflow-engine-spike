package com.example.demo.repository;

import com.example.demo.entity.Leave;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveRepository extends CrudRepository<Leave, Integer> {

}
