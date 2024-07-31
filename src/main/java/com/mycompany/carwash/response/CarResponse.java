package com.mycompany.lavajato.response;

import java.util.UUID;

import com.mycompany.lavajato.model.CarStatus;

public record CarResponse(UUID id, 
    String licensePlate, 
    String model, 
    String color, 
    CarStatus status, 
    UUID ownerId) {
}
