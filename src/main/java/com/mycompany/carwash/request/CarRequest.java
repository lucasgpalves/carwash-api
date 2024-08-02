package com.mycompany.carwash.request;

import java.util.UUID;

public record CarRequest(String licensePlate, String model, String color, UUID ownerId) {
}
