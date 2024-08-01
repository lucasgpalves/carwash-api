package com.mycompany.carwash.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Wash;

public interface WashRepository extends JpaRepository<Wash, UUID>{
    Optional<Wash> findByCarIdAndEndsAtIsNull(UUID carId);
}
