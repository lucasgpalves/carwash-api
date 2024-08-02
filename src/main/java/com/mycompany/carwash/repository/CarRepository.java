package com.mycompany.carwash.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Car;

public interface CarRepository extends JpaRepository<Car, UUID>{

}
