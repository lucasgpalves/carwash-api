package com.mycompany.carwash.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record WashCreateRequest(UUID carId, 
    String description, 
    Double amount, 
    Boolean isPaid, 
    LocalDateTime starts_at,
    LocalDateTime ends_at) {
}
