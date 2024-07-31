package com.mycompany.carwash.request;

import java.util.UUID;

import com.mycompany.carwash.model.CarStatus;

public record CarRequest(String licensePlate, String model, String color, CarStatus status, UUID ownerId) {
}
