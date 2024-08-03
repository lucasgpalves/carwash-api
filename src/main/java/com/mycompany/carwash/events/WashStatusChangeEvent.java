package com.mycompany.carwash.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import com.mycompany.carwash.model.WashStatus;

import lombok.Getter;

@Getter
public class WashStatusChangeEvent extends ApplicationEvent{
    public final UUID washId;
    public final WashStatus newStatus;  
    
    public WashStatusChangeEvent(Object source, UUID washId, WashStatus newStatus) {
        super(source);
        this.washId = washId;
        this.newStatus = newStatus;
    }
}
