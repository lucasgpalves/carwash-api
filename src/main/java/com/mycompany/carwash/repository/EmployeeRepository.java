package com.mycompany.carwash.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{
    
}
