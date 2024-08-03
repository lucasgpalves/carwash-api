package com.mycompany.carwash.listeners;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.carwash.events.WashStatusChangeEvent;
import com.mycompany.carwash.model.WashStatus;
import com.mycompany.carwash.service.WashService;

@Component
public class WashStatusChangeListener {
    
    @Autowired
    private WashService washService;

    public void handlerWashStatusChangeEvent(WashStatusChangeEvent event) {

        UUID washId = event.getWashId();
        WashStatus newStatus = event.getNewStatus();

        if(newStatus == WashStatus.DONE) {
            washService.finalizeWash(washId);
        }
    }
}
