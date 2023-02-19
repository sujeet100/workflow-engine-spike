package com.example.demo.repository;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.ProcessDef;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
}
