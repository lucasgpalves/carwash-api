package com.mycompany.carwash.response;

import java.util.UUID;

public record WashResponse(UUID washId,
    String description,
    Double amount,
    boolean isPaid,
    UUID carId) {
    
}
