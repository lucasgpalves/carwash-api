package com.mycompany.lavajato.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.lavajato.model.Car;
import com.mycompany.lavajato.model.CarStatus;

public interface CarRepository extends JpaRepository<Car, UUID>{
    List<Car> findByStatus(CarStatus status);
}
