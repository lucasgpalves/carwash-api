package com.mycompany.carwash.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.carwash.model.Wash;
import com.mycompany.carwash.model.WashStatus;

public interface WashRepository extends JpaRepository<Wash, UUID>{
    Optional<Wash> findByIdAndEndsAtIsNull(UUID id);

    List<Wash> findByStatus(WashStatus status);
}
