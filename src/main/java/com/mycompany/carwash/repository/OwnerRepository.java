package com.mycompany.lavajato.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.lavajato.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, UUID>{
    
}
