package com.mycompany.carwash.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record WashResponse(UUID washId,
    String description,
    Double amount,
    boolean isPaid,
    UUID carId,
    LocalDateTime starts_at, 
    LocalDateTime ends_at) {
    
}
