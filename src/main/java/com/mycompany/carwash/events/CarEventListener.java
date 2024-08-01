package com.mycompany.carwash.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mycompany.carwash.model.CarStatus;
import com.mycompany.carwash.service.WashService;

@Component
public class CarEventListener {
    
    @Autowired 
    WashService washService;

    @EventListener
    public void handleCarStatusChangedEvent(CarStatusChangedEvent event) {
        if (event.getNewStatus() == CarStatus.DONE) {
            washService.finalizeWash(event.getCarId());
        }
    }
}
