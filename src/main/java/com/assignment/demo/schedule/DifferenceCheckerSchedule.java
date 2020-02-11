package com.assignment.demo.schedule;

import com.assignment.demo.services.DifferenceCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DifferenceCheckerSchedule {
    @Autowired
    private DifferenceCheckerService differenceCheckerService;

    @Scheduled(fixedDelay = 30000)
    public void check() {
        differenceCheckerService.check();
    }
}
