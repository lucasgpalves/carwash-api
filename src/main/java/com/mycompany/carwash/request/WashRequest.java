package com.mycompany.lavajato.request;

import java.util.UUID;

public record WashRequest(UUID carId, String description, Double amount, Boolean isPaid) {
    
}
