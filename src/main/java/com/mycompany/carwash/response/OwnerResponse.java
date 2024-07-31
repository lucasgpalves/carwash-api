package com.mycompany.carwash.response;

import java.util.UUID;

public record OwnerResponse(UUID id, String name, String phoneNumber) {
    
}
