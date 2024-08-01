package com.mycompany.carwash.events;

import java.util.UUID;

import com.mycompany.carwash.model.CarStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarStatusChangedEvent {
    private UUID carId;
    private CarStatus newStatus;
}
