package com.project.services;

import com.dsp.service.TrimmerCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventScheduler {

    @Autowired
    TrimmerCRUDService trimmerCRUDService;

    @Scheduled(fixedDelay = 3600000)
    public void updateExpiredURL(){
        
    }

}
