package com.mycompany.carwash.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Car;
import com.mycompany.carwash.model.CarStatus;

public interface CarRepository extends JpaRepository<Car, UUID>{
    List<Car> findByStatus(CarStatus status);
}
