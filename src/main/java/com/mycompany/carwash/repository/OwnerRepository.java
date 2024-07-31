package com.mycompany.carwash.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, UUID>{
    
}
