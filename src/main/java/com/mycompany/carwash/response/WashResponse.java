package com.mycompany.carwash.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mycompany.carwash.model.WashStatus;

public record WashResponse(UUID washId,
    UUID carId,
    String description,
    Double amount,
    boolean isPaid,
    WashStatus status,
    LocalDateTime starts_at, 
    LocalDateTime ends_at) {
    
}
