package com.mycompany.lavajato.request;

import java.util.UUID;

import com.mycompany.lavajato.model.CarStatus;

public record CarRequest(String licensePlate, String model, String color, CarStatus status, UUID ownerId) {
}
