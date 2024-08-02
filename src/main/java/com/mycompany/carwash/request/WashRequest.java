package com.mycompany.carwash.request;

import java.util.UUID;

import com.mycompany.carwash.model.WashStatus;

public record WashRequest(UUID carId, 
    String description, 
    Double amount, 
    Boolean isPaid,
    WashStatus status){
    
}
