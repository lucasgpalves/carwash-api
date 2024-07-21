package com.mycompany.lavajato.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.lavajato.model.Car;

public interface CarRepository extends JpaRepository<Car, UUID>{
    
}
