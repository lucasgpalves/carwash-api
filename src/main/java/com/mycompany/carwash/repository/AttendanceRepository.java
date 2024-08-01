package com.mycompany.carwash.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID>{
    
}
