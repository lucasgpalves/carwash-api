package com.mycompany.carwash.response;

import java.util.UUID;

import com.mycompany.carwash.model.CarStatus;

public record CarResponse(UUID id, 
    String licensePlate, 
    String model, 
    String color, 
    CarStatus status, 
    UUID ownerId) {
}
