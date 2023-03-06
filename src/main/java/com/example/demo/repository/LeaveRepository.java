package com.example.demo.repository;

import com.example.demo.entity.Leave;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRepository extends CrudRepository<Leave, Integer>, ProcessEntityRepository<Leave> {

}
