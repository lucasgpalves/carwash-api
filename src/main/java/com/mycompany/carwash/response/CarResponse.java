package com.mycompany.carwash.response;

import java.util.UUID;

public record CarResponse(UUID id, 
    String licensePlate, 
    String model, 
    String color, 
    UUID ownerId) {
}
